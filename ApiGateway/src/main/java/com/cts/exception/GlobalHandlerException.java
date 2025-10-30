package com.cts.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

	@ExceptionHandler(BadRequestException.class)
	public ErrorResponse handleBadRequestException(int status,String message) {
		
		ErrorResponse errResponse=ErrorResponse.builder()
				.status(status)
				.message(message)
				.build();
		
		return errResponse;
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	
	public ErrorResponse handleUserAlreadyExistsException(int status,String message){
		
		ErrorResponse errResponse=ErrorResponse.builder()
				.status(status)
				.message(message)
				.build();
		
		return errResponse;
	}
	
}
