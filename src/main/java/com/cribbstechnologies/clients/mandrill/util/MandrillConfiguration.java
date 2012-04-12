package com.cribbstechnologies.clients.mandrill.util;

public class MandrillConfiguration {
	
	private String apiVersion;
	private String baseURL;
	private String apiKey;
	
	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	
	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}
	
	public String getServiceUrl() {
		StringBuffer sb = new StringBuffer();
		sb.append(baseURL);
		sb.append("/");
		sb.append(apiVersion);
		sb.append("/");
		return sb.toString();
	}

}
