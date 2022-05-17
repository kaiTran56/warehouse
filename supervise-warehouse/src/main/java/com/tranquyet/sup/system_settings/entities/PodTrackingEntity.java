package com.tranquyet.sup.system_settings.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tranquyet.common.entities.BasedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pod_trackings")
@Getter
@Setter
public class PodTrackingEntity extends BasedEntity {
	@Column
	private Long podsId;
	@Column
	private Long productsId;
	@Column
	private LocalDateTime completedTime;
}
