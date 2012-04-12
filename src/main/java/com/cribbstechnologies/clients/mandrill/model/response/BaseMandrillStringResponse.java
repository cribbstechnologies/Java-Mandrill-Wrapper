package com.cribbstechnologies.clients.mandrill.model.response;

public class BaseMandrillStringResponse extends BaseMandrillResponse {

	private String response;
	
	public BaseMandrillStringResponse() {
		
	}
	
	public BaseMandrillStringResponse(String response) {
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
}
