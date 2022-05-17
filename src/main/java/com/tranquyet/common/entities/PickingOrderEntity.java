package com.tranquyet.common.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "picking_orders")
@Getter
@Setter
public class PickingOrderEntity extends BasedEntity {
	@Column(nullable = false, unique = true)
	private String prCodeProduct;
	@Column(nullable = false, unique = true)
	private String qrCodePod;
	@Column
	private Integer quantity;
	@Column
	private LocalDateTime completedTime;
}
