package com.tranquyet.sup.operations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tranquyet.common.entities.PickingOrderEntity;

@Repository
public interface PickingRepository extends JpaRepository<PickingOrderEntity, Long> {
	@Query(value = "select * from picking_orders where delete_status = 1;", nativeQuery = true)
	List<PickingOrderEntity> getAll();

	@Modifying
	@Query(value = "update picking_orders set status = :statusOrder where id = :idOrder", nativeQuery = true)
	void updateStatus(@Param("statusOrder") String status, @Param("idOrder") Long id);
}
