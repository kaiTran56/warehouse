package com.tranquyet.sup.converts;

import org.springframework.stereotype.Component;

import com.tranquyet.common.dtos.RoleDTO;
import com.tranquyet.sup.entities.RoleEntity;

@Component
public class RoleConvert {
	public RoleDTO toDTO(RoleEntity entity) {
		RoleDTO dto = new RoleDTO();
		dto.setId(entity.getId());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setModifiedBy(entity.getModifiedBy());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setDeleteStatus(entity.getDeleteStatus());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		return dto;
	}

	public RoleEntity toEntity(RoleDTO dto) {
		RoleEntity entity = new RoleEntity();
		entity.setId(dto.getId());
		entity.setDeleteStatus(dto.getDeleteStatus());
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		return entity;
	}
}
