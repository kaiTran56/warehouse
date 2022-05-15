package com.tranquyet.sup.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tranquyet.sup.entities.UserEntity;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	@Query(value = "select * from users where delete_status = 1;", nativeQuery = true)
	List<UserEntity> getAll();

	Optional<UserEntity> findByUsername(String username);

	Optional<UserEntity> findByGmail(String email);

	Boolean existsByGmail(String email);

	@Query(value = "select working from users where id = :idUser and delete_status = 1;", nativeQuery = true)
	Long isWorking(@Param("idUser") Long idUser);

	@Modifying
	@Query(value = "update users set delete_status = 0 where id = :idUser", nativeQuery = true)
	void updateDeleteStatus(@Param("idUser") Long id);

	@Modifying
	@Query(value = "update users set working = :working where id = :idUser", nativeQuery = true)
	void updateWorking(@Param("idUser") Long id, @Param("working") Long working);
}