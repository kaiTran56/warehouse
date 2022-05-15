package com.tranquyet.sup.converts;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tranquyet.common.dtos.RoleDTO;
import com.tranquyet.common.dtos.UserDTO;
import com.tranquyet.sup.entities.RoleEntity;
import com.tranquyet.sup.entities.UserEntity;

@Component
public class UserConvert {
	@Autowired
	private RoleConvert roleConvert;

	public UserDTO toDTO(UserEntity entity) {
		UserDTO dto = new UserDTO();
		List<RoleDTO> roles = entity.getRoles().stream().map(p -> roleConvert.toDTO(p)).collect(Collectors.toList());
		dto.setId(entity.getId());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedBy(entity.getModifiedBy());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setDeleteStatus(entity.getDeleteStatus());
		dto.setUsername(entity.getUsername());
		dto.setGmail(entity.getGmail());
		dto.setRoles(roles);
		return dto;
	}

	public UserEntity toEntity(UserDTO dto) {
		UserEntity entity = new UserEntity();
		List<RoleEntity> roles = dto.getRoles().stream().map(p -> roleConvert.toEntity(p)).collect(Collectors.toList());
		entity.setId(dto.getId());
		entity.setDeleteStatus(dto.getDeleteStatus());
		entity.setUsername(dto.getUsername());
		entity.setPassword(dto.getPassword());
		entity.setGmail(dto.getGmail());
		entity.setRoles(roles);
		return entity;
	}
}
