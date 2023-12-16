package com.app.stock.globalexceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.stock.exception.CustomException;
import com.app.stock.exception.PasswordMissmatchException;
import com.app.stock.exception.UserNotFound;

@ControllerAdvice
public class UserExceptionHandler {

	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<Object> exception() {
		return new ResponseEntity<>("Person Already Exists!!!!", HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = UserNotFound.class)
	public ResponseEntity<Object> userNotFoundException() {
		return new ResponseEntity<>("Person Not Found!!!!", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = PasswordMissmatchException.class)
	public ResponseEntity<Object> passwordNotMatch() {
		return new ResponseEntity<>("Please Enter Correct Password...", HttpStatus.NOT_FOUND);
	}

}
