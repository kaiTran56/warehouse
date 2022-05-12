package com.tranquyet.sup.security.service;

import org.springframework.stereotype.Service;

import com.tranquyet.sup.domains.UserSummary;
import com.tranquyet.sup.security.UserPrincipal;

@Service
public class UserService {

	public UserSummary getCurrentUser(UserPrincipal userPrincipal) {
		return UserSummary.builder().id(userPrincipal.getId()).email(userPrincipal.getEmail())
				.name(userPrincipal.getName()).build();
	}
}
