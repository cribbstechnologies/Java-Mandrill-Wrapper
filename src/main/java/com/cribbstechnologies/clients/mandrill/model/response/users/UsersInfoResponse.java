package com.cribbstechnologies.clients.mandrill.model.response.users;

import com.cribbstechnologies.clients.mandrill.model.response.BaseMandrillResponse;
import com.cribbstechnologies.clients.mandrill.model.response.StatsResponseMap;

public class UsersInfoResponse extends BaseMandrillResponse {

	String username;
	String created_at;
	int reputation;
	int hourly_quota;
	int backlog;
	
	StatsResponseMap stats;

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getCreated_at() {
		return created_at;
	}
	
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	public int getHourly_quota() {
		return hourly_quota;
	}

	public void setHourly_quota(int hourly_quota) {
		this.hourly_quota = hourly_quota;
	}

	public StatsResponseMap getStats() {
		return stats;
	}

	public void setStats(StatsResponseMap stats) {
		this.stats = stats;
	}

	public int getBacklog() {
		return backlog;
	}

	public void setBacklog(int backlog) {
		this.backlog = backlog;
	}
	
	

}
