package com.connection.Practice.exception;
public class DateException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public DateException() {}
	public DateException(String msg) {
		super(msg);
	}
}
