package com.app.stock.globalexceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.stock.exception.CustomException;
import com.app.stock.exception.PasswordMissmatchException;
import com.app.stock.exception.UserNotFound;

//Global Exception Handler for User
@ControllerAdvice
public class UserExceptionHandler {

	/*
	 * Handle CustomException.
	 * Return ResponseEntity with a message and BAD_REQUEST status.
	 */
	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<Object> exception() {
		return new ResponseEntity<>("Person Already Exists!!!!", HttpStatus.BAD_REQUEST);

	}

	/*
	 * Handle UserNotFound exception.
	 *Return ResponseEntity with a message and NOT_FOUND status.
	 */

	@ExceptionHandler(value = UserNotFound.class)
	public ResponseEntity<Object> userNotFoundException() {
		return new ResponseEntity<>("Person Not Found!!!!", HttpStatus.NOT_FOUND);
	}

	/*
	 * Handle PasswordMissmatchException.
	 * Return ResponseEntity with a message and NOT_FOUND status.
	 */

	@ExceptionHandler(value = PasswordMissmatchException.class)
	public ResponseEntity<Object> passwordNotMatch() {
		return new ResponseEntity<>("Please Enter Correct Password...", HttpStatus.NOT_FOUND);
	}

}
