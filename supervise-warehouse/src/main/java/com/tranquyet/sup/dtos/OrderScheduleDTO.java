package com.tranquyet.sup.dtos;

import java.time.LocalDateTime;

import com.tranquyet.common.dtos.BasedDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderScheduleDTO extends BasedDTO<OrderScheduleDTO> {
	private String orderCode;
	private LocalDateTime timeRelease;
	private String source;
	private String staff;
	private String statusOrder;
	private String customerName;
	private String phone;
	private String address;
	private String productCode;
	private String productName;
	private String productNote;
	private String orderNote;
	private String customerNote;
	private String statusOrderSchedule;
}
