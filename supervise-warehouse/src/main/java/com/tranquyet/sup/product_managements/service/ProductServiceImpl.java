package com.tranquyet.sup.product_managements.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tranquyet.sup.enums.StatusWorking;
import com.tranquyet.sup.product_managements.entities.ProductEntity;
import com.tranquyet.sup.product_managements.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepo;

	@Override
	public List<ProductEntity> getAll() {
		return productRepo.getAll();
	}

	@Override
	public ProductEntity getById(Long id) {
		return productRepo.findById(id).get();
	}

	@Override
	public ProductEntity save(ProductEntity entity) {
		ProductEntity temp = new ProductEntity();
		if (entity.getId() != null) {
			ProductEntity old = productRepo.findById(entity.getId()).get();
			temp = old;
			temp = entity;
		} else {
			temp = entity;
		}
		temp.setDeleteStatus(1);
		temp.setWorking((int) StatusWorking.NOT_WORKING.getKey());
		return productRepo.save(temp);
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			productRepo.updateDeleteStatus(id);
		}

	}

	@Override
	public Boolean checkWorking(Long id) {
		if (id != null) {
			return productRepo.isWorking(id) == StatusWorking.WORKING.getKey() ? true : false;
		}
		return false;
	}

	@Override
	public List<ProductEntity> getProductByPodId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
