package com.assignment.student.exception;

import org.springframework.http.HttpStatus;

public class StudentApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6233133514394983492L;
	private HttpStatus httpStatus;
	private String message;

	public StudentApplicationException(HttpStatus httpStatus, String message) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
