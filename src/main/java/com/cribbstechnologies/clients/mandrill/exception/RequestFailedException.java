package com.cribbstechnologies.clients.mandrill.exception;

import com.cribbstechnologies.clients.mandrill.model.MandrillError;

public class RequestFailedException extends Throwable {

	private static final long serialVersionUID = 1L;
        private MandrillError error;

	public RequestFailedException(String message, Throwable t) {
		super(message, t);
	}
	
	public RequestFailedException(String message) {
		super(message);
	}
        
        public RequestFailedException(String message, MandrillError error) {
		super(message);
                this.error = error;
	}
        
        public MandrillError getError() {
            return error;
        }
}
