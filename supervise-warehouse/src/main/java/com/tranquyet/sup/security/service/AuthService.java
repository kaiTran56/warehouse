package com.tranquyet.sup.security.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tranquyet.sup.constants.PropertyConstants;
import com.tranquyet.sup.domains.JwtAuthenticationResponse;
import com.tranquyet.sup.domains.LoginRequest;
import com.tranquyet.sup.domains.SignUpRequest;
import com.tranquyet.sup.entities.RoleEntity;
import com.tranquyet.sup.entities.UserEntity;
import com.tranquyet.sup.exception.AppException;
import com.tranquyet.sup.exception.ConflictException;
import com.tranquyet.sup.repository.PermissionRepository;
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
	private RoleRepository roleRepo;
	private PermissionRepository permissionRepo;

	@Autowired
	public AuthService(@Lazy AuthenticationManager authenticationManager, @Lazy UserRepository userRepository,
			@Lazy RoleRepository roleRepository, @Lazy PasswordEncoder passwordEncoder,
			@Lazy JwtTokenProvider tokenProvider, @Lazy RoleRepository roleRepo,
			@Lazy PermissionRepository permissionRepo) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
		this.roleRepo = roleRepo;
		this.permissionRepo = permissionRepo;
	}

	public JwtAuthenticationResponse authenticateUser(LoginRequest login) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(login.getGmail(), login.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.generateToken(authentication);
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		log.info("User with [email: {}] has logged in", userPrincipal.getEmail());

		return new JwtAuthenticationResponse(jwt, userPrincipal.getId());
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

		user.setRoles(Collections.singletonList(userRole));
		return userRepository.save(user).getId();
	}

	public String hasRoles(String screen) {
		StringBuilder rolesProperties = new StringBuilder();
		rolesProperties.append("hasAnyAuthority").append("(");
		Long idPer = permissionRepo.getPermissisonId(screen);
		List<String> roles = roleRepo.findRoleByPermission(idPer);
		log.info(roles.toString());
		IntStream.range(0, roles.size()).forEach(p -> {
			rolesProperties.append("\"").append(roles.get(p)).append("\"");
			if (p < roles.size() - 1) {
				rolesProperties.append(PropertyConstants.COMMA_SYM);
			}
		});

		rolesProperties.append(")");
		log.info(rolesProperties.toString());
		return rolesProperties.toString();
	}

}
