package com.tranquyet.sup.system_settings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tranquyet.sup.system_settings.entities.PodEntity;

@Repository
public interface PodRepository extends JpaRepository<PodEntity, Long> {
	@Query(value = "select working from pods where id = :idPod and delete_status = 1;", nativeQuery = true)
	Long isWorking(@Param("idPod") Long idPod);

	@Query(value = "select * from pods where delete_status = 1;", nativeQuery = true)
	List<PodEntity> getAll();

	@Modifying
	@Query(value = "update pods set delete_status = 0 where id = :idPod", nativeQuery = true)
	void updateDeleteStatus(@Param("idPod") Long id);
}
