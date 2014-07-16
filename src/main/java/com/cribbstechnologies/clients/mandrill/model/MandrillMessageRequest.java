package com.cribbstechnologies.clients.mandrill.model;

public class MandrillMessageRequest extends BaseMandrillRequest {
	
	private MandrillHtmlMessage message;
        protected Boolean async;

	public MandrillMessage getMessage() {
		return message;
	}

	public void setMessage(MandrillHtmlMessage message) {
		this.message = message;
	}

        public Boolean getAsync() {
            return async;
        }

        public void setAsync(Boolean async) {
            this.async = async;
        }
	
}
