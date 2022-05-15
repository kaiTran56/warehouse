package com.tranquyet.sup.domains;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationResponse {
	private Long id;
	private String accessToken;
	private String tokenType = "Bearer";

	public JwtAuthenticationResponse(String accessToken, Long id) {
		this.accessToken = accessToken;
		this.id = id;
	}
}
