package com.tranquyet.sup.operations.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tranquyet.common.entities.PickingOrderEntity;
import com.tranquyet.sup.enums.PickingStatus;
import com.tranquyet.sup.operations.domain.OrderStatus;
import com.tranquyet.sup.operations.repository.PickingRepository;
import com.tranquyet.sup.product_managements.entities.ProductEntity;
import com.tranquyet.sup.product_managements.repository.ProductRepository;
import com.tranquyet.sup.system_settings.entities.PodEntity;
import com.tranquyet.sup.system_settings.repository.PodRepository;
import com.tranquyet.sup.utils.CustomFormatDate;

@Service
@Transactional
public class PickingServiceImpl implements PickingService {
	@Autowired
	private PickingRepository pickingRepo;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private PodRepository podRepo;

	@Override
	public List<PickingOrderEntity> getAll() {

		return pickingRepo.getAll();
	}

	@Override
	public PickingOrderEntity getById(@NotNull Long id) {

		return pickingRepo.findById(id).get();
	}

	@Override
	public PickingOrderEntity save(PickingOrderEntity picking) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void updateStatus(OrderStatus orderStatus) throws Exception {
		PickingOrderEntity picking = pickingRepo.findById(orderStatus.getId()).get();
		switch (PickingStatus.fromKey(orderStatus.getStatus())) {
		case PICKED_STATUS -> {

			Integer check = podRepo.quantityCapability(picking.getQrCodePod());
			if (podRepo.quantityCapability(picking.getQrCodePod()) <= 0) {
				throw new Exception("Pod donnot have enough space");
			}
			check = check - 1;
			podRepo.updateQuantity(check, picking.getQrCodePod());
		}
		case CANCELLED_STATUS -> {
			ProductEntity product = productRepo.findByQrCode(picking.getQrCodeProduct());
			productRepo.updateDeleteStatus(product.getId());
		}

		}
		pickingRepo.updateStatus(orderStatus.getStatus(), orderStatus.getId());
	}

	@Override
	public PickingOrderEntity save(@NotNull ProductEntity product) throws Exception {
		product.setDeleteStatus(1);
		product.setWorking(0);
		PodEntity pod = podRepo.searchSuitablePod(product.getWidth(), product.getHeight(), product.getLength(), 1);
		if (pod == null) {
			throw new Exception("Not have available POD!");
		}

		if (podRepo.quantityCapability(pod.getQrCode()) <= 0) {
			throw new Exception("Pod donnot have enough space");
		}
		if (productRepo.findByQrCode(product.getQrCode()) != null) {
			throw new Exception("Existed Product!");
		}

		product.setPod(pod);
		product.setId(null);
		productRepo.save(product);

		PickingOrderEntity pick = new PickingOrderEntity();
		pick.setId(null);
		pick.setStatus(PickingStatus.PROCESSING_STATUS.getKey());
		pick.setDeleteStatus(1);
		pick.setStartedTime(CustomFormatDate.convertDateToLocalDateTime(new Date()));
		pick.setCompletedTime(CustomFormatDate.convertDateToLocalDateTime(new Date()));
		pick.setQrCodePod(pod.getQrCode());
		pick.setQrCodeProduct(product.getQrCode());
		pick.setQuantityProduct(product.getQuantity());
		pick.setNote(product.getNote());
		pickingRepo.save(pick);
		return null;
	}

}
