package com.cribbstechnologies.clients.mandrill.model.response;

public class StatsResponse extends BaseMandrillResponse {

	int sent;
	int hard_bounces;
	int soft_bounces;
	int rejects;
	int complaints;
	int unsubs;
	int opens;
	int unique_opens;
	int clicks;
	int unique_clicks;

	public int getSent() {
		return sent;
	}
	
	public void setSent(int sent) {
		this.sent = sent;
	}
	
	public int getHard_bounces() {
		return hard_bounces;
	}
	
	public void setHard_bounces(int hard_bounces) {
		this.hard_bounces = hard_bounces;
	}
	
	public int getSoft_bounces() {
		return soft_bounces;
	}
	
	public void setSoft_bounces(int soft_bounces) {
		this.soft_bounces = soft_bounces;
	}
	
	public int getRejects() {
		return rejects;
	}
	
	public void setRejects(int rejects) {
		this.rejects = rejects;
	}
	
	public int getComplaints() {
		return complaints;
	}
	
	public void setComplaints(int complaints) {
		this.complaints = complaints;
	}
	
	public int getUnsubs() {
		return unsubs;
	}
	
	public void setUnsubs(int unsubs) {
		this.unsubs = unsubs;
	}
	
	public int getOpens() {
		return opens;
	}
	
	public void setOpens(int opens) {
		this.opens = opens;
	}
	
	public int getUnique_opens() {
		return unique_opens;
	}
	
	public void setUnique_opens(int unique_opens) {
		this.unique_opens = unique_opens;
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
	
}
