package com.tranquyet.common.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO extends BasedDTO<UserDTO> {

	private String username;

	private String gmail;

	private String password;

	private List<RoleDTO> roles = new ArrayList<>();
}
