package com.tranquyet.sup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tranquyet.sup.entities.UserRoleEntity;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
	@Query(value = "delete from users_roles where users_id = :idUser", nativeQuery = true)
	void deleteByUserId(@Param("idUser") Long id);

	@Modifying
	@Query(value = "update users_roles set roles_id = :idRole where users_id = :idUser", nativeQuery = true)
	void updateUserRole(@Param("idRole") Long idRole, @Param("idUser") Long idUser);

	@Query(value = "select roles_id from users_roles where roles_id = :id", nativeQuery = true)
	Long isExist(@Param("id") Long id);
}