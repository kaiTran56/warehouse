package com.tranquyet.sup.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tranquyet.sup.entities.UserEntity;
import com.tranquyet.sup.exception.NotFoundException;
import com.tranquyet.sup.repository.UserRepository;
import com.tranquyet.sup.security.UserPrincipal;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) {
		UserEntity user = userRepo.findByGmail(email)
				.orElseThrow(() -> new NotFoundException("User not found [email: " + email + "]"));

		return UserPrincipal.create(user);
	}

	@Transactional
	public UserDetails loadUserById(Long id) {
		UserEntity user = userRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("User not found [id: " + id + "]"));

		return UserPrincipal.create(user);
	}

}
