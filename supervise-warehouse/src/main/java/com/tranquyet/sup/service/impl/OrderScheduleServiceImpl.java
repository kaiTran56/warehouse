package com.tranquyet.sup.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tranquyet.sup.converts.OrderScheduleConvert;
import com.tranquyet.sup.dtos.OrderScheduleDTO;
import com.tranquyet.sup.repo.custom.OrderScheduleRepository;
import com.tranquyet.sup.service.OrderScheduleService;

@Service
public class OrderScheduleServiceImpl implements OrderScheduleService {
	@Autowired
	private OrderScheduleConvert orderConvert;
	@Autowired
	private OrderScheduleRepository orderScheduleRepo;

	@Override
	public void save(List<OrderScheduleDTO> orders) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long[] ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<OrderScheduleDTO> getAll() {
		List<OrderScheduleDTO> orders = orderScheduleRepo.getAll().stream().map(p -> orderConvert.toDTO(p))
				.collect(Collectors.toList());
//		CSVHelper.writeCsvFile(orders);
		return orders;
	}

	@Override
	public List<OrderScheduleDTO> getByPhone(String phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderScheduleDTO> getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderScheduleDTO> getOntimeSchedule(Integer hepaTime, Integer normalTime) {
		List<OrderScheduleDTO> orders = orderScheduleRepo.getOrderOnSchedule(hepaTime, normalTime).stream()
				.map(p -> orderConvert.toDTO(p)).collect(Collectors.toList());
		return orders;
	}

}
