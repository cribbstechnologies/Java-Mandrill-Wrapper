package com.cribbstechnologies.clients.mandrill.exception;

public class RequestFailedException extends Throwable {

	private static final long serialVersionUID = 1L;

	public RequestFailedException(String message, Throwable t) {
		super(message, t);
	}
	
	public RequestFailedException(String message) {
		super(message);
	}
}
