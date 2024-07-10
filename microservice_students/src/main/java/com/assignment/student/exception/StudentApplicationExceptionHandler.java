package com.assignment.student.exception;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentApplicationExceptionHandler {
	
	//Custom Exception Handler
	@ExceptionHandler(value = { StudentApplicationException.class })
	public ResponseEntity<Object> handleStudentApplicationException(	
			StudentApplicationException userApplicationException) {
		return ResponseEntity.status(userApplicationException.getHttpStatus())
				.body(userApplicationException.getMessage());
	}
    
	//File Not Found Exception Handler
	@ExceptionHandler(value = { FileNotFoundException.class })
	public ResponseEntity<Object> handleRuntimeException(FileNotFoundException fileNotFoundException) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");

	}
    
	//IO Exception Handler
	@ExceptionHandler(value = { IOException.class })
	public ResponseEntity<Object> handleRuntimeException(IOException fileNotFoundException) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong");

	}
}
