package com.tranquyet.sup.enums;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum RolePermission {
	ORDER_SCHEDULES("/api/order-schedule/**", "PER0001"), LOGIN("/api/auth/signin", ""), LOGOUT("/api/auth/logout", ""),
	CURRENT_USER_INFOR("/api/user/**", ""), REGISTER("/api/auth/signup", ""),
	ROLE_MANAGEMENT("/api/role/**", "PER0002"), REGISTER_CUSTOMER("/api/auth/signup/customer", "");

	private String url;
	private String permission;

	RolePermission(String url, String role) {
		this.url = url;
		this.permission = role;
	}

	public static RolePermission fromUrl(String url) {
		return Arrays.stream(values()).filter(p -> p.url.equalsIgnoreCase(url)).findFirst().get();
	}

	public RolePermission fromPermission(String per) {
		return Arrays.stream(values()).filter(p -> p.permission.equalsIgnoreCase(per)).findFirst().get();
	}
}
