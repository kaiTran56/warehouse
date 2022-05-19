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

import com.tranquyet.common.entities.StowingOrderEntity;
import com.tranquyet.sup.enums.ErrorCommon;
import com.tranquyet.sup.enums.StowingStatus;
import com.tranquyet.sup.operations.domain.OrderStatus;
import com.tranquyet.sup.operations.service.StowingService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/stowing")
@Slf4j
public class StowingController {
	@Autowired
	private StowingService stowingService;

	@GetMapping("/list")
	public ResponseEntity<List<StowingOrderEntity>> getAll() {
		return new ResponseEntity<>(stowingService.getAll(), HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<?> updateStatus(@RequestBody OrderStatus orderStatus) {
		try {
			StowingOrderEntity stwOrder = stowingService.getById(orderStatus.getId());
			if (stwOrder == null) {
				return new ResponseEntity<>(ErrorCommon.NOT_FOUND.getValue(), HttpStatus.BAD_REQUEST);
			}
			if (stwOrder.getStatus().equalsIgnoreCase(StowingStatus.STOWED_STATUS.getKey())) {
				return new ResponseEntity<>(ErrorCommon.CANNOT_COMPLETED.getValue(), HttpStatus.BAD_REQUEST);
			} else if (stwOrder.getStatus().equalsIgnoreCase(StowingStatus.CANCELLED_STATUS.getKey())) {
				return new ResponseEntity<>(ErrorCommon.CANNOT_CANCELLED.getValue(), HttpStatus.BAD_REQUEST);
			}
			stowingService.updateStatus(orderStatus);
			return new ResponseEntity<>("Successfully!", HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(e, HttpStatus.OK);
		}

	}
}
