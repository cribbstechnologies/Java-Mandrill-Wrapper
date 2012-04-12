package com.cribbstechnologies.clients.mandrill.model.response.users;

import com.cribbstechnologies.clients.mandrill.model.response.StatsResponse;

public class MandrillSender extends StatsResponse {

	//{"address":"federico@mailchimp.com","created_at":"2012-01-09 15:29:19","is_enabled":true}
	String address;
	String created_at;
	boolean is_enabled;

	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCreated_at() {
		return created_at;
	}
	
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	public boolean getIs_enabled() {
		return is_enabled;
	}
	
	public void setIs_enabled(boolean is_enabled) {
		this.is_enabled = is_enabled;
	}
}
