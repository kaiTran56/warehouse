package com.tranquyet.sup.enums;

import lombok.Getter;

@Getter
public enum StatusOrderEnum {
	PROCESSING("processing", "Đang giao dịch"), COMPLETE("complete", "Hoàn thành"), CANCEL("cancel", "Đã hủy"),
	WAITING("WAITING", "đang chờ"), DENY("DENY", "từ chối");

	private String key;
	private String value;

	StatusOrderEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

}
