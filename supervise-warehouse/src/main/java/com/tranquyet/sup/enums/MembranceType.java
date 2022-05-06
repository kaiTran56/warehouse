package com.tranquyet.sup.enums;

import lombok.Getter;

@Getter
public enum MembranceType {
	HEPA_TYPE("hepa");

	private String key;

	MembranceType(String key) {
		this.key = key;
	}
}
