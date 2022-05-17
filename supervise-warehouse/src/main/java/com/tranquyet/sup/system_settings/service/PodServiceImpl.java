package com.tranquyet.sup.system_settings.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tranquyet.sup.enums.StatusWorking;
import com.tranquyet.sup.product_managements.entities.ProductEntity;
import com.tranquyet.sup.product_managements.repository.ProductRepository;
import com.tranquyet.sup.system_settings.entities.PodEntity;
import com.tranquyet.sup.system_settings.repository.PodRepository;

@Service
@Transactional
public class PodServiceImpl implements PodService {
	@Autowired
	private PodRepository podRepo;

	@Autowired
	private ProductRepository productRepo;

	@Override
	public List<PodEntity> getAll() {

		return podRepo.getAll();
	}

	@Override
	public PodEntity save(PodEntity entity) {
		PodEntity temp = new PodEntity();
		if (entity.getId() != null) {
			PodEntity old = podRepo.findById(entity.getId()).get();
			temp = old;
			temp = entity;
		} else {
			temp = entity;
		}
		temp.setDeleteStatus(1);
		temp.setWorking((int) StatusWorking.NOT_WORKING.getKey());
		return podRepo.save(temp);
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			podRepo.updateDeleteStatus(id);
		}
	}

	@Override
	public PodEntity getById(Long id) {

		return podRepo.findById(id).orElse(null);
	}

	@Override
	public Boolean checkWorking(Long id) {
		if (id != null) {
			return podRepo.isWorking(id) == StatusWorking.WORKING.getKey() ? true : false;
		}
		return false;
	}

	@Override
	public List<ProductEntity> getProductByPodId(Long id) {
		return productRepo.getProductByPodId(id);
	}

}
