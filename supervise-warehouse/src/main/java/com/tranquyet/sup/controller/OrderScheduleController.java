package com.tranquyet.sup.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//		try {
//			emailService.sendAttachmentMessage(null);
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return new ResponseEntity<>(orderOp.get(), orderOp.isPresent() ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
	}
}
