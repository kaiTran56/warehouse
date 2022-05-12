package com.tranquyet.sup.domains;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

	@NotBlank
	private String gmail;

	@NotBlank
	private String password;
}
