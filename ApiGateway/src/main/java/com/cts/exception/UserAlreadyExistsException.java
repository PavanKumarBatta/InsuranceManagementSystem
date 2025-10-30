package com.cts.exception;

public class UserAlreadyExistsException extends RuntimeException {

	private String message;
	private int status;
	
	public UserAlreadyExistsException(int status,String message) {
		this.message=message;
		this.status=status;
	}
}
