package com.tranquyet.sup.service;

import java.util.List;

import com.tranquyet.sup.dtos.OrderScheduleDTO;

public interface OrderScheduleService {
	void save(List<OrderScheduleDTO> orders);

	void delete(Long[] ids);

	List<OrderScheduleDTO> getAll();

	List<OrderScheduleDTO> getByPhone(String phone);

	List<OrderScheduleDTO> getById(Long id);

	List<OrderScheduleDTO> getOntimeSchedule();
}
