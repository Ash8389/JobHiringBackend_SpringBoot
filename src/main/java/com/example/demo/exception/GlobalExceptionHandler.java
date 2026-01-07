package com.example.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.model.CustomError;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class); 
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomError> resourceNotfound(ResourceNotFoundException ex) {
		
		log.error("Ressource Not found: {}", ex.getMessage());
		
		CustomError error = new CustomError(ex.getMessage(), 404, System.currentTimeMillis());
		
		return ResponseEntity.status(404).body(error);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomError> AllException(Exception ex) {
		
		log.error("Ressource Not found: {}", ex.getMessage());
		
		CustomError error = new CustomError(ex.getMessage(), 404, System.currentTimeMillis());
		
		return ResponseEntity.status(500).body(error);
		
	}
}
