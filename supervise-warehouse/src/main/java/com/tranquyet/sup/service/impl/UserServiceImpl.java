package com.tranquyet.sup.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tranquyet.common.dtos.UserDTO;
import com.tranquyet.sup.converts.UserConvert;
import com.tranquyet.sup.entities.RoleEntity;
import com.tranquyet.sup.entities.UserEntity;
import com.tranquyet.sup.entities.UserRoleEntity;
import com.tranquyet.sup.enums.StatusWorking;
import com.tranquyet.sup.repository.RoleRepository;
import com.tranquyet.sup.repository.UserRepository;
import com.tranquyet.sup.repository.UserRoleRepository;
import com.tranquyet.sup.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserConvert userConvert;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private UserRoleRepository userRoleRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<UserDTO> getAll() {
		return userRepo.getAll().stream().map(p -> userConvert.toDTO(p)).collect(Collectors.toList());
	}

	@Override
	public Boolean checkWorking(Long id) {
		if (id != null) {
			return userRepo.isWorking(id) == StatusWorking.WORKING.getKey() ? true : false;
		}
		return false;
	}

	@Override
	@Transactional
	public UserDTO save(UserDTO dto) {
		UserEntity temp = new UserEntity();
		RoleEntity role = roleRepo.findByName(dto.getRoles().get(0).getName()).get();

		if (dto.getId() != null) {
			UserEntity old = userRepo.findById(dto.getId()).orElse(null);
//			temp = userConvert.toEntity(dto);
			temp = old;
			temp.setUsername(dto.getUsername());
			temp.setDeleteStatus(1);
			temp.setWorking((int) StatusWorking.NOT_WORKING.getKey());
			userRepo.saveAndFlush(temp);
			userRoleRepo.updateUserRole(role.getId(), temp.getId());
		} else {
			temp = userConvert.toEntity(dto);
			temp.setPassword(passwordEncoder.encode(dto.getPassword()));
			temp.setRoles(null);
			temp.setDeleteStatus(1);
			temp.setWorking((int) StatusWorking.NOT_WORKING.getKey());
			temp = userRepo.saveAndFlush(temp);

			UserRoleEntity userRole = new UserRoleEntity();
			UserRoleEntity.PrimaryKey prKey = new UserRoleEntity.PrimaryKey();
			prKey.setRolesId(role.getId());
			prKey.setUsersId(temp.getId());
			userRole.setKeys(prKey);
			userRoleRepo.save(userRole);
		}

		return null;
	}

	@Override
	public UserDTO getById(Long id) {
		return userConvert.toDTO(userRepo.findById(id).get());
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			userRepo.updateDeleteStatus(id);
		}
	}

	@Override
	public void updateWorking(Long idStatus, Long idUser) {
		userRepo.updateWorking(idStatus, idUser);
	}

}
