package com.tranquyet.sup.service;

import java.util.List;

import com.tranquyet.sup.domains.OrderScheduleQuery;
import com.tranquyet.sup.domains.OrderScheduleSub;
import com.tranquyet.sup.dtos.OrderScheduleDTO;

public interface OrderScheduleService {
	void save(List<OrderScheduleDTO> orders);

	void save(OrderScheduleDTO dto);

	void updateActionOrderSchedule(OrderScheduleSub dto) throws Exception;

	void delete(Long[] ids);

	void deleteAll();

	List<OrderScheduleDTO> getAll();

	List<OrderScheduleDTO> getByPhone(String phone);

	OrderScheduleDTO getById(Long id);

	List<OrderScheduleDTO> getByPhoneAndProductCode(OrderScheduleQuery query);

	List<OrderScheduleDTO> getOntimeSchedule(Integer hepaTime, Integer normalTime);
}
