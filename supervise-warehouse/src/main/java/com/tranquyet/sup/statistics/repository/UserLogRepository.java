package com.tranquyet.sup.statistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tranquyet.sup.statistics.entities.UserLogEntity;

@Repository
public interface UserLogRepository extends JpaRepository<UserLogEntity, Long> {

}
