package com.tranquyet.sup.security.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tranquyet.sup.domains.JwtAuthenticationResponse;
import com.tranquyet.sup.domains.LoginRequest;
import com.tranquyet.sup.domains.SignUpRequest;
import com.tranquyet.sup.entities.RoleEntity;
import com.tranquyet.sup.entities.UserEntity;
import com.tranquyet.sup.exception.AppException;
import com.tranquyet.sup.exception.ConflictException;
import com.tranquyet.sup.repository.RoleRepository;
import com.tranquyet.sup.repository.UserRepository;
import com.tranquyet.sup.security.UserPrincipal;
import com.tranquyet.sup.security.jwt.JwtTokenProvider;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private JwtTokenProvider tokenProvider;

	@Autowired
	public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository,
			RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
	}

	public JwtAuthenticationResponse authenticateUser(LoginRequest login) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(login.getGmail(), login.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.generateToken(authentication);
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		log.info("User with [email: {}] has logged in", userPrincipal.getEmail());

		return new JwtAuthenticationResponse(jwt);
	}

	public Long registerUser(SignUpRequest signupReq) {
		if (userRepository.existsByGmail(signupReq.getEmail())) {
			throw new ConflictException("Email [email: " + signupReq.getEmail() + "] is already taken");
		}
		UserEntity user = new UserEntity();
		user.setUsername(signupReq.getName());
		user.setGmail(signupReq.getEmail());
		user.setPassword(passwordEncoder.encode(signupReq.getPassword()));
		RoleEntity userRole = roleRepository.findByName(signupReq.getRole())
				.orElseThrow(() -> new AppException("User Role not set. Add default roles to database."));

		user.setRoles(Collections.singleton(userRole));
		return userRepository.save(user).getId();
	}

}
