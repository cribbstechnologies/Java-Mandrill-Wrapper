package com.cribbstechnologies.clients.mandrill.model;

public class MandrillResponse {

	private String responseString;
	private boolean success;
	
	public String getJsonResponse() {
		return responseString;
	}
	
	public void setResponseString(String jsonResponse) {
		this.responseString = jsonResponse;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
