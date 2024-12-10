package com.srss_frontend.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends Exception {
	private static final long serialVersionUID = 356174818196868116L;

	private HttpStatus httpStatus;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
