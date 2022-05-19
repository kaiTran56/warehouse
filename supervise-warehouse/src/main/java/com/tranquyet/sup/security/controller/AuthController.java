package com.tranquyet.sup.security.controller;

import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tranquyet.sup.annotation.CurrentUser;
import com.tranquyet.sup.domains.JwtAuthenticationResponse;
import com.tranquyet.sup.domains.LoginRequest;
import com.tranquyet.sup.domains.SignUpRequest;
import com.tranquyet.sup.domains.UserSummary;
import com.tranquyet.sup.entities.UserEntity;
import com.tranquyet.sup.enums.ActionLog;
import com.tranquyet.sup.enums.StatusWorking;
import com.tranquyet.sup.repository.UserRepository;
import com.tranquyet.sup.security.UserPrincipal;
import com.tranquyet.sup.security.service.AuthService;
import com.tranquyet.sup.service.UserService;
import com.tranquyet.sup.statistics.entities.UserLogEntity;
import com.tranquyet.sup.statistics.service.UserLogService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthService authService;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private UserLogService userLogService;

	@Autowired
	private com.tranquyet.sup.security.service.UserService userServiceSec;

	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
		JwtAuthenticationResponse jwtAuth = authService.authenticateUser(loginRequest);
		if (jwtAuth == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} else {
			UserEntity userEntity = userRepo.findByGmail(loginRequest.getGmail()).get();
			userService.updateWorking(userEntity.getId(), StatusWorking.WORKING.getKey());
			UserLogEntity userLog = new UserLogEntity();
			userLog.setIdUser(userEntity.getId());
			userLog.setAction(ActionLog.LOGIN_ACT.getKey());
			userLogService.save(userLog);
			return new ResponseEntity<>(jwtAuth, HttpStatus.OK);
		}

	}

	@GetMapping("/logout")
	public void logout(@CurrentUser UserPrincipal currentUser) {

		UserSummary currUser = userServiceSec.getCurrentUser(currentUser);
		if (currentUser != null) {
			UserEntity userEntity = userRepo.findByGmail(currUser.getEmail()).get();
			userService.updateWorking(userEntity.getId(), StatusWorking.NOT_WORKING.getKey());
			UserLogEntity userLog = new UserLogEntity();
			userLog.setIdUser(userEntity.getId());
			userLog.setAction(ActionLog.LOGOUT_ACT.getKey());
			userLogService.save(userLog);
		}

	}

	@PostMapping("/signup")
	@ResponseStatus(OK)
	public Long register(@Valid @RequestBody SignUpRequest signUpRequest) {
		return authService.registerUser(signUpRequest);
	}

	@PostMapping("/signup/customer")
	@ResponseStatus(OK)
	public Long registerCustomer(@Valid @RequestBody SignUpRequest signUpRequest) {
		signUpRequest.setRole("ROLE_CUSTOMER");
		return authService.registerUser(signUpRequest);
	}
}
