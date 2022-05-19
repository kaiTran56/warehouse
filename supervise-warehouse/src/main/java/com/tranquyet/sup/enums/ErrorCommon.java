package com.tranquyet.sup.enums;

import lombok.Getter;

@Getter
public enum ErrorCommon {
	CANNOT_COMPLETED("not.permit.completed", "Status of order was already COMPLETED!"),
	CANNOT_CANCELLED("not.permit.cancelled", "Status of order was already CANCELLED!"),
	NOT_FOUND("not.found", "Cannot find the object!");

	private String key;
	private String value;

	ErrorCommon(String key, String value) {
		this.key = key;
		this.value = value;
	}

}
