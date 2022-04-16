package com.tranquyet.sup.service;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tranquyet.sup.converts.OrderScheduleConvert;
import com.tranquyet.sup.dtos.OrderScheduleDTO;
import com.tranquyet.sup.repo.custom.OrderScheduleRepository;
import com.tranquyet.sup.utils.CSVHelper;

@Service
public class CSVService {
	@Autowired
	private OrderScheduleConvert orderScheduleConvert;
	@Autowired
	private OrderScheduleRepository repository;

	public ByteArrayInputStream load() {
		List<OrderScheduleDTO> orders = repository.findAll().stream().map(p -> orderScheduleConvert.toDTO(p))
				.collect(Collectors.toList());
		ByteArrayInputStream in = CSVHelper.transferDbsToCsv(orders);
		return in;
	}
}
