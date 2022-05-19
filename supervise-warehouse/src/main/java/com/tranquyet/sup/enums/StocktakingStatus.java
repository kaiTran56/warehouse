package com.tranquyet.sup.enums;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum StocktakingStatus {
	STOCKTAKED_STATUS("STOCKTAKED"), PROCESSING_STATUS("PROCESSING"), CANCELLED_STATUS("CANCELLED"), NEW_STATUS("NEW");

	private String key;

	StocktakingStatus(String key) {
		this.key = key;
	}

	public static StocktakingStatus fromKey(String key) {
		return Arrays.stream(values()).filter(p -> p.key.equalsIgnoreCase(key)).findFirst().get();
	}
}
