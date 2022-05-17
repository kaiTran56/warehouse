package com.tranquyet.sup.system_settings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody RoleDTO dto) {
		roleService.save(dto);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getRole(@PathVariable Long id) {
		RoleDTO dto = roleService.getById(id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Long[] ids = { id };
		if (roleService.isWorking(id)) {
			return new ResponseEntity<>("Role has been used!", HttpStatus.OK);
		}
		roleService.delete(ids);
		return new ResponseEntity<>(1, HttpStatus.OK);
	}

}
