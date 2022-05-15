package com.tranquyet.sup.service;

import java.util.List;

import com.tranquyet.common.dtos.RoleDTO;

public interface RoleService {
	List<RoleDTO> getAll();

	RoleDTO save(RoleDTO dto);

	RoleDTO getById(Long id);

	Boolean isWorking(Long id);

	void delete(Long[] ids);
}
