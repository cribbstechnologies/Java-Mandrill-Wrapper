package com.cribbstechnologies.clients.mandrill.exception;

public class RequestFailedException extends Exception {

	private static final long serialVersionUID = 1L;

	public RequestFailedException(String message, Throwable t) {
		super(message, t);
	}
	
	public RequestFailedException(String message) {
		super(message);
	}
}
