package com.tranquyet.sup.system_settings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tranquyet.sup.system_settings.entities.PodEntity;

@Repository
public interface PodRepository extends JpaRepository<PodEntity, Long> {

}
