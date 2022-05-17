package com.tranquyet.sup.product_managements.service;

import java.util.List;

import com.tranquyet.sup.product_managements.entities.ProductEntity;

public interface ProductService {
	List<ProductEntity> getAll();

	ProductEntity getById(Long id);

	ProductEntity save(ProductEntity entity);

	void delete(Long[] ids);

	Boolean checkWorking(Long id);

	List<ProductEntity> getProductByPodId(Long id);
}
