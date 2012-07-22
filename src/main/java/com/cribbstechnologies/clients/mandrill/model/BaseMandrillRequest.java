package com.cribbstechnologies.clients.mandrill.model;

public class BaseMandrillRequest {

	/*
	 * It's not necessary to set this field manually as the MandrillRESTRequest pulls the property
	 * from the configuration and populates this object with it on every request.
	 */
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
