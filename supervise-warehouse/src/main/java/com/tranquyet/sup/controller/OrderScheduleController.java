package com.tranquyet.sup.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranquyet.sup.domains.OrderScheduleQuery;
import com.tranquyet.sup.domains.OrderScheduleSub;
import com.tranquyet.sup.dtos.OrderScheduleDTO;
import com.tranquyet.sup.service.OrderScheduleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/order-schedule")
public class OrderScheduleController {
	@Autowired
	private OrderScheduleService orderScheduleService;

	@GetMapping
	public ResponseEntity<OrderScheduleDTO> renderOrderSchedules() {
		OrderScheduleDTO order = new OrderScheduleDTO();
		order.setListResult(orderScheduleService.getAll());
		Optional<OrderScheduleDTO> orderOp = Optional.of(order);
		return new ResponseEntity<>(orderOp.get(), orderOp.isPresent() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<OrderScheduleDTO> getDetailOrderSchedule(@PathVariable(required = true) Long id) {
		final HttpStatus[] httpStatuses = { null };
		Optional<OrderScheduleDTO> dto = Optional.of(orderScheduleService.getById(id));
		dto.ifPresent(p -> {
			httpStatuses[0] = HttpStatus.OK;
		});
		return new ResponseEntity<>(dto.get(), Optional.of(httpStatuses[0]).orElse(HttpStatus.EXPECTATION_FAILED));
	}

	@PostMapping("/search")
	public ResponseEntity<OrderScheduleDTO> getListOrderSchedule(
			@RequestBody(required = true) OrderScheduleQuery query) {
		final HttpStatus[] httpStatuses = { null };
		Optional<List<OrderScheduleDTO>> schedules = Optional.of(orderScheduleService.getByPhoneAndProductCode(query));
		OrderScheduleDTO order = new OrderScheduleDTO();
		order.setListResult(schedules.get());
		Optional.of(order).ifPresent(p -> {
			httpStatuses[0] = HttpStatus.OK;
		});
		return new ResponseEntity<>(order, Optional.of(httpStatuses[0]).orElse(HttpStatus.EXPECTATION_FAILED));

	}

	@PostMapping("/update")
	public ResponseEntity<?> updateOrderSchedule(@RequestBody(required = true) OrderScheduleSub query) {
		System.out.println(query);
		try {
			OrderScheduleDTO order = new OrderScheduleDTO();
			orderScheduleService.updateActionOrderSchedule(query);
			return new ResponseEntity<>(query, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}

	}
}
