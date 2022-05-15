package com.tranquyet.sup.enums;

import lombok.Getter;

@Getter
public enum ActionLog {
	LOGIN_ACT("LOGIN_SUP"), LOGOUT_ACT("LOGOUT_SUP");

	private String key;

	ActionLog(String key) {
		this.key = key;
	}
}
