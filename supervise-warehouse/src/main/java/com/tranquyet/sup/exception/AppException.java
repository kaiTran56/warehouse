package com.tranquyet.sup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AppException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8380264052518563612L;

	public AppException(String message) {
		super(message);
	}
}