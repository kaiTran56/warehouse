package com.tranquyet.common.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoleDTO extends BasedDTO<RoleDTO> {

	private String name;

	private String description;

}
