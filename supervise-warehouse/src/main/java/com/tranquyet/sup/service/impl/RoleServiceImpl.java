package com.tranquyet.sup.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tranquyet.common.dtos.RoleDTO;
import com.tranquyet.sup.converts.RoleConvert;
import com.tranquyet.sup.repository.RoleRepository;
import com.tranquyet.sup.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private RoleConvert roleConvert;

	@Override
	public List<RoleDTO> getAll() {
		return roleRepo.getAll().stream().map(p -> roleConvert.toDTO(p)).collect(Collectors.toList());
	}

}
