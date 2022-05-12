package com.tranquyet.sup.exception;

public class BadRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2164038873850708580L;

	public BadRequestException(String message) {
		super(message);
	}
}
