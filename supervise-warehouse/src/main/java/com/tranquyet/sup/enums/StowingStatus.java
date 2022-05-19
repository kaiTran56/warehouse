package com.tranquyet.sup.enums;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum StowingStatus {
	STOWED_STATUS("STOWED"), PROCESSING_STATUS("PROCESSING"), CANCELLED_STATUS("CANCELLED"), NEW_STATUS("NEW");

	private String key;

	StowingStatus(String key) {
		this.key = key;
	}

	public static StowingStatus fromKey(String key) {
		return Arrays.stream(values()).filter(p -> p.key.equalsIgnoreCase(key)).findFirst().get();
	}
}
