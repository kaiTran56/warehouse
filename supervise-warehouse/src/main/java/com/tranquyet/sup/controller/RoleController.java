package com.tranquyet.sup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranquyet.common.dtos.RoleDTO;
import com.tranquyet.sup.service.RoleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/role")
public class RoleController {
	@Autowired
	private RoleService roleService;

	@GetMapping("/list")
	public ResponseEntity<RoleDTO> getRoles() {
		List<RoleDTO> dtos = roleService.getAll();
		RoleDTO dto = new RoleDTO();
		dto.setListResult(dtos);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
