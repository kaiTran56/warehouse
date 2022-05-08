package com.tranquyet.sup.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.Size;

import org.springframework.util.StringUtils;

import com.tranquyet.common.dtos.BasedDTO;
import com.tranquyet.sup.domains.OrderScheduleSub;
import com.tranquyet.sup.enums.MembranceType;
import com.tranquyet.sup.enums.TimeRelease;
import com.tranquyet.sup.utils.CustomFormatDate;

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
	@Size(max = 200)
	private String productNote;
	@Size(max = 200)
	private String orderNote;
	@Size(max = 200)
	private String customerNote;
	private String statusOrderSchedule;

	public String getNextTimeRelease() {
		if (timeRelease == null) {
			return null;
		}
		LocalDateTime tempTime = StringUtils.hasText(productName)
				&& productName.contains(MembranceType.HEPA_TYPE.getKey())
						? timeRelease.plusDays(TimeRelease.HEPA_TIME.getKey())
						: timeRelease.plusDays(TimeRelease.NORMAL_TIME.getKey());
		return CustomFormatDate.formatLocalDateTime(tempTime, CustomFormatDate.FORMAT_DD_MM_YYYY);
	}

	public void update(OrderScheduleSub sub) {
		this.customerNote = sub.getCustomerNote();
		this.productNote = sub.getProductNote();
		this.statusOrderSchedule = sub.getStatusOrderSchedule();
	}

//	public LocalDateTime getTimeRelease() {
//		return CustomFormatDate.convertStringToDate(timeRelease.toString(), CustomFormatDate.FORMAT_DD_MM_YYYY_HHmmss);
//	}
}
