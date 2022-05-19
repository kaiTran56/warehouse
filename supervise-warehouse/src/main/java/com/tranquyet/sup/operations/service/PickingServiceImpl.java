package com.tranquyet.sup.operations.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tranquyet.common.entities.PickingOrderEntity;
import com.tranquyet.sup.operations.domain.OrderStatus;
import com.tranquyet.sup.operations.repository.PickingRepository;

@Service
@Transactional
public class PickingServiceImpl implements PickingService {
	@Autowired
	private PickingRepository pickingRepo;

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

	@Override
	public void updateStatus(OrderStatus orderStatus) throws Exception {
		pickingRepo.updateStatus(orderStatus.getStatus(), orderStatus.getId());
	}

}
