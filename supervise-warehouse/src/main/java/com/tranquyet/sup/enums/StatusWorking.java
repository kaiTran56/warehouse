package com.tranquyet.sup.enums;

import lombok.Getter;

@Getter
public enum StatusWorking {
	WORKING(1), NOT_WORKING(0);

	private long key;

	StatusWorking(long i) {
		this.key = i;
	}
}
