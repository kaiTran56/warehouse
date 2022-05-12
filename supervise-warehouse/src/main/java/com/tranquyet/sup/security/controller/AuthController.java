package com.tranquyet.sup.security.controller;

import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tranquyet.sup.domains.JwtAuthenticationResponse;
import com.tranquyet.sup.domains.LoginRequest;
import com.tranquyet.sup.domains.SignUpRequest;
import com.tranquyet.sup.security.service.AuthService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/signin")
	@ResponseStatus(OK)
	public JwtAuthenticationResponse login(@Valid @RequestBody LoginRequest loginRequest) {
		return authService.authenticateUser(loginRequest);
	}

	@PostMapping("/signup")
	@ResponseStatus(OK)
	public Long register(@Valid @RequestBody SignUpRequest signUpRequest) {
		return authService.registerUser(signUpRequest);
	}
}
