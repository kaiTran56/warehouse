package com.tranquyet.sup.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tranquyet.common.entities.BasedEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_schedules")
public class OrderScheduleEntity extends BasedEntity {
	@Column
	private String orderCode;
	@Column
	private LocalDateTime timeRelease;
	@Column
	private String source;
	@Column
	private String staff;
	@Column
	private String statusOrder;
	@Column
	private String customerName;
	@Column
	private String phone;
	@Column
	private String address;
	@Column
	private String productCode;
	@Column
	private String productName;
	@Column(columnDefinition = "TEXT")
	private String productNote;
	@Column(columnDefinition = "TEXT")
	private String orderNote;
	@Column(columnDefinition = "TEXT")
	private String customerNote;
	@Column
	private String statusOrderSchedule;
}
