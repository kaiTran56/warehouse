package com.tranquyet.sup.repo.custom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.tranquyet.sup.entities.OrderScheduleEntity;

@Repository
public interface OrderScheduleRepository
		extends JpaRepository<OrderScheduleEntity, Long>, JpaSpecificationExecutor<OrderScheduleEntity> {

}
