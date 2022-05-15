package com.tranquyet.sup.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tranquyet.common.dtos.RoleDTO;
import com.tranquyet.sup.converts.RoleConvert;
import com.tranquyet.sup.entities.RoleEntity;
import com.tranquyet.sup.repository.RoleRepository;
import com.tranquyet.sup.repository.UserRoleRepository;
import com.tranquyet.sup.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private UserRoleRepository userRoleRepo;

	@Autowired
	private RoleConvert roleConvert;

	@Override
	public List<RoleDTO> getAll() {
		return roleRepo.getAll().stream().map(p -> roleConvert.toDTO(p)).collect(Collectors.toList());
	}

	@Override
	public RoleDTO save(RoleDTO dto) {
		RoleEntity temp = new RoleEntity();
		if (dto.getId() != null) {
			RoleEntity old = roleRepo.findById(dto.getId()).get();
			old.setName(roleConvert.toEntity(dto).getName());
			temp = old;

		} else {
			temp = roleConvert.toEntity(dto);
		}
		temp.setDeleteStatus(1);
		return roleConvert.toDTO(roleRepo.save(temp));
	}

	@Override
	public RoleDTO getById(Long id) {
		return roleConvert.toDTO(roleRepo.findById(id).get());
	}

	@Override
	public Boolean isWorking(Long id) {
		return userRoleRepo.isExist(id) != null ? true : false;
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			roleRepo.deleteById(id);
		}

	}

}
