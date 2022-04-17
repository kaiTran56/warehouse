package com.tranquyet.sup.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tranquyet.sup.dtos.OrderScheduleDTO;
import com.tranquyet.sup.enums.TimeRelease;
import com.tranquyet.sup.utils.CSVHelper;

@Service
public class CSVService {
	@Autowired
	private OrderScheduleService orderSheService;

	public ByteArrayInputStream load() {
		List<OrderScheduleDTO> orders = orderSheService.getOntimeSchedule(TimeRelease.HEPA_TIME.getKey(),
				TimeRelease.NORMAL_TIME.getKey());
		ByteArrayInputStream in = CSVHelper.transferDbsToCsv(orders);
		return in;
	}
}
