package com.app.stock.globalexceptionhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.stock.exception.AdminNotFound;
import com.app.stock.exception.CustomException;
import com.app.stock.exception.MobileNumberException;
import com.app.stock.exception.PasswordMissmatchException;

@ControllerAdvice
public class AdminExceptionHandler {

	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<Object> exception(CustomException ce) {
		return new ResponseEntity<>(ce.getMessage(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(value = AdminNotFound.class)
	public ResponseEntity<Object> userNotFoundException(AdminNotFound an) {
		return new ResponseEntity<>(an.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = PasswordMissmatchException.class)
	public ResponseEntity<Object> passwordNotMatch(PasswordMissmatchException pe) {
		return new ResponseEntity<>(pe.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = MobileNumberException.class)
	public ResponseEntity<Object> mobilenumberexists(MobileNumberException me) {
		return new ResponseEntity<>(me.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
