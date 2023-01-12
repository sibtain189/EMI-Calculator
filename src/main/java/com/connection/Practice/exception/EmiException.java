package com.connection.Practice.exception;

/**
 * This class for exception and its extends to runtime exception
 */
public class EmiException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EmiException() {
	}

	public EmiException(String msg) {
		super(msg);
	}
}
