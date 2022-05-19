package com.tranquyet.sup.operations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranquyet.common.entities.PickingOrderEntity;
import com.tranquyet.sup.enums.ErrorCommon;
import com.tranquyet.sup.enums.PickingStatus;
import com.tranquyet.sup.operations.domain.OrderStatus;
import com.tranquyet.sup.operations.service.PickingService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/picking")
@Slf4j
public class PickingController {
	@Autowired
	private PickingService pickingService;

	@GetMapping("/list")
	public ResponseEntity<List<PickingOrderEntity>> getAll() {
		return new ResponseEntity<>(pickingService.getAll(), HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<?> updateStatus(@RequestBody OrderStatus orderStatus) {
		try {
			PickingOrderEntity pckOrder = pickingService.getById(orderStatus.getId());
			if (pckOrder == null) {
				return new ResponseEntity<>(ErrorCommon.NOT_FOUND.getValue(), HttpStatus.BAD_REQUEST);
			}
			if (pckOrder.getStatus().equalsIgnoreCase(PickingStatus.PICKED_STATUS.getKey())) {
				return new ResponseEntity<>(ErrorCommon.CANNOT_COMPLETED.getValue(), HttpStatus.BAD_REQUEST);
			} else if (pckOrder.getStatus().equalsIgnoreCase(PickingStatus.CANCELLED_STATUS.getKey())) {
				return new ResponseEntity<>(ErrorCommon.CANNOT_CANCELLED.getValue(), HttpStatus.BAD_REQUEST);
			}
			pickingService.updateStatus(orderStatus);
			return new ResponseEntity<>("Successfully!", HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(e, HttpStatus.OK);
		}

	}

}
