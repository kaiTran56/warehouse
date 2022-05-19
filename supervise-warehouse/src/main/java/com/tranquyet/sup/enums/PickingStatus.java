package com.tranquyet.sup.enums;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum PickingStatus {
	PICKED_STATUS("PICKED"), PROCESSING_STATUS("PROCESSING"), CANCELLED_STATUS("CANCELLED"), NEW_STATUS("NEW");

	private String key;

	PickingStatus(String key) {
		this.key = key;
	}

	public static PickingStatus fromKey(String key) {
		return Arrays.stream(values()).filter(p -> p.key.equalsIgnoreCase(key)).findFirst().get();
	}
}
