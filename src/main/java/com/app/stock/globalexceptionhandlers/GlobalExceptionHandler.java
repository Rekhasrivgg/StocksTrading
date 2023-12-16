package com.app.stock.globalexceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.app.stock.exception.CustomException;
import com.app.stock.exception.ReviewNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<Object> exception(CustomException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> handleException(Exception e) {

		return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
