package com.tranquyet.sup.operations.service;

import java.util.List;

import com.tranquyet.common.entities.StocktakingOrderEntity;
import com.tranquyet.sup.operations.domain.OrderStatus;

public interface StocktakingService {
	List<StocktakingOrderEntity> getAll();

	StocktakingOrderEntity getById(Long id);

	StocktakingOrderEntity save(StocktakingOrderEntity picking);

	void updateStatus(OrderStatus orderStatus) throws Exception;

	void delete(Long[] ids);
}
