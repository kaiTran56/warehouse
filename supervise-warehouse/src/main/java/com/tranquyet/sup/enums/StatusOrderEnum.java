package com.tranquyet.sup.enums;

public enum StatusOrderEnum {
	PROCESSING("processing", "Đang giao dịch"), COMPLETE("complete", "Hoàn thành"), CANCEL("cancel", "Đã hủy");

	private String key;
	private String value;

	StatusOrderEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}
