package com.tranquyet.sup.enums;

import lombok.Getter;

@Getter
public enum TimeRelease {
	HEPA_TIME(63), NORMAL_TIME(93);

	private Integer key;

	TimeRelease(Integer key) {
		this.key = key;
	}

}
