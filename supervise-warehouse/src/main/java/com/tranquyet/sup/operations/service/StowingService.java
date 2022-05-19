package com.tranquyet.sup.operations.service;

import java.util.List;

import com.tranquyet.common.entities.StowingOrderEntity;
import com.tranquyet.sup.operations.domain.OrderStatus;

public interface StowingService {
	List<StowingOrderEntity> getAll();

	StowingOrderEntity getById(Long id);

	StowingOrderEntity save(StowingOrderEntity picking);

	void updateStatus(OrderStatus orderStatus) throws Exception;

	void delete(Long[] ids);
}
