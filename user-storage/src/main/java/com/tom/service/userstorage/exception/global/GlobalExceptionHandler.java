package com.tom.service.userstorage.exception.global;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tom.service.userstorage.exception.ErrorResponse;
import com.tom.service.userstorage.exception.IncorrectPasswordException;
import com.tom.service.userstorage.exception.InvalidPasswordException;
import com.tom.service.userstorage.exception.JWTFilterException;
import com.tom.service.userstorage.exception.NotFoundException;
import com.tom.service.userstorage.exception.PasswordUpdateException;
import com.tom.service.userstorage.exception.UnableToCheckToken;
import com.tom.service.userstorage.exception.UnableToLogoutException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ NotFoundException.class })
	public ResponseEntity<String> handleNotFoundException(RuntimeException exp) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(((GlobalRuntimeException) exp).getMsg());
	}

	@ExceptionHandler({ PasswordUpdateException.class, UnableToLogoutException.class, UnableToCheckToken.class, JWTFilterException.class })
	public ResponseEntity<String> handleServiceException(RuntimeException exp) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(((GlobalRuntimeException) exp).getMsg());
	}
	
	@ExceptionHandler({ InvalidPasswordException.class })
	public ResponseEntity<String> handleInvalidPasswordException(IllegalStateException exp) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(((GlobalIllegalStateException) exp).getMsg());
	}
	
	@ExceptionHandler({ IncorrectPasswordException.class })
	public ResponseEntity<String> handleIncorrectPasswordException(IllegalStateException exp) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(((GlobalIllegalStateException) exp).getMsg());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exp) {

		var errors = new HashMap<String, String>();

		exp.getBindingResult().getAllErrors().forEach(error -> {
			var fieldName = ((FieldError) error).getField();
			var errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
	}

}