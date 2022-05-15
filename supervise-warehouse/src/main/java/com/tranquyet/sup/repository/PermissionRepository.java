package com.tranquyet.sup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tranquyet.sup.entities.PermissionEntity;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
	Optional<PermissionEntity> findByName(String roleName);

	@Query(value = "select id from permissions where name like :name", nativeQuery = true)
	Long getPermissisonId(@Param("name") String name);
}