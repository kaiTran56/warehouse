package com.tranquyet.sup.repo.custom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tranquyet.sup.entities.OrderScheduleEntity;

@Repository
public interface OrderScheduleRepository
		extends JpaRepository<OrderScheduleEntity, Long>, JpaSpecificationExecutor<OrderScheduleEntity> {
	@Query(value = "SELECT * FROM order_schedules ord WHERE ord.delete_status = 1 ", nativeQuery = true)
	List<OrderScheduleEntity> getAll();

	@Query(value = "select * from orderOnSchedule(:hepaTime, :normalTime);", nativeQuery = true)
	List<OrderScheduleEntity> getOrderOnSchedule(@Param("hepaTime") Integer hepaTime,
			@Param("normalTime") Integer normalTime);
}
