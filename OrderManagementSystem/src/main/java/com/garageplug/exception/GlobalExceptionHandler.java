package com.garageplug.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<ExceptionDetails> customerExceptionHandler(CustomerException ce, WebRequest request){
		
		ExceptionDetails details = new ExceptionDetails();
		details.setMessage(ce.getMessage());
		details.setTimestamp(LocalDateTime.now());
		details.setDetails(request.getDescription(false));
		
		return new ResponseEntity<ExceptionDetails>(details, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DiscountException.class)
	public ResponseEntity<ExceptionDetails> customerExceptionHandler(DiscountException de, WebRequest request){
		
		ExceptionDetails details = new ExceptionDetails();
		details.setMessage(de.getMessage());
		details.setTimestamp(LocalDateTime.now());
		details.setDetails(request.getDescription(false));
		
		return new ResponseEntity<ExceptionDetails>(details, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<ExceptionDetails> customerExceptionHandler(OrderException oe, WebRequest request){
		
		ExceptionDetails details = new ExceptionDetails();
		details.setMessage(oe.getMessage());
		details.setTimestamp(LocalDateTime.now());
		details.setDetails(request.getDescription(false));
		
		return new ResponseEntity<ExceptionDetails>(details, HttpStatus.BAD_REQUEST);
	}
	
}
