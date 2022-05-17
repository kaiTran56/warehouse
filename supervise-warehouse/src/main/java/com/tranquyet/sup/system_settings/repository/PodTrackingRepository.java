package com.tranquyet.sup.system_settings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tranquyet.sup.system_settings.entities.PodTrackingEntity;

@Repository
public interface PodTrackingRepository extends JpaRepository<PodTrackingEntity, Long> {

}
