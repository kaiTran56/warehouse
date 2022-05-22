package com.tranquyet.sup.operations.service;

import java.util.List;

import com.tranquyet.common.entities.PickingOrderEntity;
import com.tranquyet.sup.operations.domain.OrderStatus;
import com.tranquyet.sup.product_managements.entities.ProductEntity;

public interface PickingService {
	List<PickingOrderEntity> getAll();

	PickingOrderEntity getById(Long id);

	PickingOrderEntity save(PickingOrderEntity picking);

	PickingOrderEntity save(ProductEntity product) throws Exception;

	void updateStatus(OrderStatus orderStatus) throws Exception;

	void delete(Long[] ids);
}
