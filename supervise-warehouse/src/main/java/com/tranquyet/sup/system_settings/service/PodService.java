package com.tranquyet.sup.system_settings.service;

import java.util.List;

import com.tranquyet.sup.product_managements.entities.ProductEntity;
import com.tranquyet.sup.system_settings.entities.PodEntity;

public interface PodService {
	List<PodEntity> getAll();

	PodEntity getById(Long id);

	PodEntity save(PodEntity entity);

	void delete(Long[] ids);

	Boolean checkWorking(Long id);

	List<ProductEntity> getProductByPodId(Long id);
}
