package com.tranquyet.sup.statistics.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tranquyet.sup.statistics.entities.UserLogEntity;
import com.tranquyet.sup.statistics.repository.UserLogRepository;

@Service
@Transactional
public class UserLogServiceImpl implements UserLogService {

	@Autowired
	private UserLogRepository userLogRepo;

	@Override
	public List<UserLogEntity> getAll() {
		return userLogRepo.findAll();
	}

	@Override
	public UserLogEntity save(UserLogEntity entity) {
		entity.setId(null);
		return userLogRepo.save(entity);
	}

}
