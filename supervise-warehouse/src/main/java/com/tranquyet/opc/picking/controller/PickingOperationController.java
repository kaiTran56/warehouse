package com.tranquyet.opc.picking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranquyet.sup.operations.service.PickingService;
import com.tranquyet.sup.product_managements.entities.ProductEntity;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/opc/picking")
@Slf4j
public class PickingOperationController {
	@Autowired
	private PickingService pickingService;

	@PostMapping("/save")
	public ResponseEntity<?> updateStatus(@RequestBody ProductEntity product) {
		try {
//			PickingOrderEntity pckOrder = pickingService.getById(orderStatus.getId());
//			if (pckOrder == null) {
//				return new ResponseEntity<>(ErrorCommon.NOT_FOUND.getValue(), HttpStatus.BAD_REQUEST);
//			}
//			if (pckOrder.getStatus().equalsIgnoreCase(PickingStatus.PICKED_STATUS.getKey())) {
//				return new ResponseEntity<>(ErrorCommon.CANNOT_COMPLETED.getValue(), HttpStatus.BAD_REQUEST);
//			} else if (pckOrder.getStatus().equalsIgnoreCase(PickingStatus.CANCELLED_STATUS.getKey())) {
//				return new ResponseEntity<>(ErrorCommon.CANNOT_CANCELLED.getValue(), HttpStatus.BAD_REQUEST);
//			}
//			pickingService.updateStatus(orderStatus);0
			pickingService.save(product);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}

	}
}
