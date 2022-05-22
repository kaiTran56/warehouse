package com.tranquyet.common.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "picking_orders")
@Getter
@Setter
@NoArgsConstructor
public class PickingOrderEntity extends BasedEntity {
	@Column(unique = false)
	private String qrCodeProduct;
	@Column(unique = false)
	private String qrCodePod;
	@Column
	private Integer quantityProduct;
	@Column
	private LocalDateTime completedTime;
	@Column
	private LocalDateTime startedTime;
	@Column
	private String note;
}
