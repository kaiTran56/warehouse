package com.tranquyet.sup.service;

import java.util.List;

import com.tranquyet.common.dtos.UserDTO;

public interface UserService {
	List<UserDTO> getAll();

	Boolean checkWorking(Long id);

	UserDTO save(UserDTO dto);

	UserDTO getById(Long id);

	void delete(Long[] ids);

	void updateWorking(Long idUser, Long idStatus);
}
