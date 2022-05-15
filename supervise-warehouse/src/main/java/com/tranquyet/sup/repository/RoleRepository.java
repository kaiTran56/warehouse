package com.tranquyet.sup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tranquyet.sup.entities.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	Optional<RoleEntity> findByName(String roleName);

	@Query(value = "select * from roles " + "where delete_status = 1", nativeQuery = true)
	List<RoleEntity> getAll();

	@Query(value = "select roles.name from roles inner join roles_permissions "
			+ "on roles.id = roles_permissions.roles_id "
			+ "where roles_permissions.permissions_id = :idPer and delete_status = 1", nativeQuery = true)
	List<String> findRoleByPermission(@Param("idPer") Long idPer);
}