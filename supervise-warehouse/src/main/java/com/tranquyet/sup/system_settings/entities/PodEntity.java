package com.tranquyet.sup.system_settings.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tranquyet.common.entities.BasedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pods")
@Getter
@Setter
public class PodEntity extends BasedEntity {
	@Column(unique = true, nullable = false)
	private String qrCode;
	@Column
	private Integer storageQuantity;
	@Column
	private Double width;
	@Column
	private Double height;
}
