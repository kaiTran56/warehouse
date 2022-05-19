package com.tranquyet.sup.statistics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranquyet.sup.statistics.entities.UserLogEntity;
import com.tranquyet.sup.statistics.service.UserLogService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user-log")
public class UserLogController {
	@Autowired
	private UserLogService userLogService;

	@GetMapping("/list")
	public ResponseEntity<List<UserLogEntity>> getAll() {
		return new ResponseEntity<>(userLogService.getAll(), HttpStatus.OK);
	}
}
