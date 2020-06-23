package com.payclip.assessment.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value = TransactionNotFoundException.class)
	public ResponseEntity<Object> exception(TransactionNotFoundException exception) {
		return new ResponseEntity<>("No transactions found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = InvalidRequestException.class)
	public ResponseEntity<Object> exception(InvalidRequestException exception) {
		return new ResponseEntity<>(exception.getMessage() != null ? exception.getMessage() : "Invalid Request", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = NoSuchElementException.class)
	public ResponseEntity<Object> exception(NoSuchElementException exception) {
		return new ResponseEntity<>("Transaction not found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
		e.printStackTrace();
		return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}