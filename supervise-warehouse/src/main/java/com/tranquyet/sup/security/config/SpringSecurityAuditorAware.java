package com.tranquyet.sup.security.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.tranquyet.sup.security.UserPrincipal;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}
		if (authentication.getPrincipal() == "anonymousUser") {
			return Optional.of("anonymousUser");
		}
		return Optional.of(((UserPrincipal) authentication.getPrincipal()).getName());
	}

}