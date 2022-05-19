package com.tranquyet.sup.statistics.service;

import java.util.List;

import com.tranquyet.sup.statistics.entities.UserLogEntity;

public interface UserLogService {
	List<UserLogEntity> getAll();

	UserLogEntity save(UserLogEntity entity);
}
