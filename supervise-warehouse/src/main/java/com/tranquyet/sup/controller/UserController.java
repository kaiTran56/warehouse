package com.tranquyet.sup.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

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

import com.tranquyet.common.dtos.UserDTO;
import com.tranquyet.sup.annotation.CurrentUser;
import com.tranquyet.sup.domains.UserSummary;
import com.tranquyet.sup.security.UserPrincipal;
import com.tranquyet.sup.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private com.tranquyet.sup.security.service.UserService userServiceSec;

	@Autowired
	private UserService userService;

	@GetMapping("/list")
	public ResponseEntity<UserDTO> getAllUsers() {
		List<UserDTO> dtos = userService.getAll();
		UserDTO user = new UserDTO();
		user.setListResult(dtos);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		UserDTO user = userService.getById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody UserDTO dto) {
		if (dto.getId() == null) {
			userService.save(dto);
			return new ResponseEntity<>(1, HttpStatus.OK);
		}
		if (userService.checkWorking(dto.getId()) == true) {
			return new ResponseEntity<>("User is working!", HttpStatus.FORBIDDEN);
		}
		userService.save(dto);

		return new ResponseEntity<>(1, HttpStatus.OK);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		if (userService.checkWorking(id) == true) {
			return new ResponseEntity<>("User is working!", HttpStatus.FORBIDDEN);
		}
		Long[] ids = { id };
		userService.delete(ids);
		return new ResponseEntity<>("Successfully!", HttpStatus.OK);
	}

	@GetMapping("/me")
	public UserSummary getCurrentUser(@NotNull @CurrentUser UserPrincipal currentUser) {
		return userServiceSec.getCurrentUser(currentUser);
	}

}
