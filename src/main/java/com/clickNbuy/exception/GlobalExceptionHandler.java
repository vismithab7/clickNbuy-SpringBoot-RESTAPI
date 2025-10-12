package com.clickNbuy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.clickNbuy.dto.ErrorDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code= HttpStatus.BAD_REQUEST)
	public ErrorDto handle(MethodArgumentNotValidException exception) {
		return new ErrorDto(exception.getMessage());
	}
	
	@ExceptionHandler(DataExitsException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public ErrorDto handle(DataExitsException exception) {
		return new ErrorDto(exception.getMessage());
	}

}
