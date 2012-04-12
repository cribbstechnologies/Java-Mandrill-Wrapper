package com.cribbstechnologies.clients.mandrill.model.response.urls;

public class BaseUrlResponse {
	
	String id;
	int sent;
	int clicks;
	int unique_clicks;

	public int getSent() {
		return sent;
	}
	
	public void setSent(int sent) {
		this.sent = sent;
	}
	
	public int getClicks() {
		return clicks;
	}
	
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
	
	public int getUnique_clicks() {
		return unique_clicks;
	}
	
	public void setUnique_clicks(int unique_clicks) {
		this.unique_clicks = unique_clicks;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
