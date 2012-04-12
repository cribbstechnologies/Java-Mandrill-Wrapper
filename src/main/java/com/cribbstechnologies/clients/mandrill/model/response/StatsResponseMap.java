package com.cribbstechnologies.clients.mandrill.model.response;

public class StatsResponseMap {

	StatsResponse today;
	StatsResponse last_7_days;
	StatsResponse last_30_days;
	StatsResponse last_60_days;
	StatsResponse last_90_days;
	StatsResponse all_time;

	public StatsResponse getToday() {
		return today;
	}

	public void setToday(StatsResponse today) {
		this.today = today;
	}
	
	public StatsResponse getLast_7_days() {
		return last_7_days;
	}
	
	public void setLast_7_days(StatsResponse last_7_days) {
		this.last_7_days = last_7_days;
	}
	
	public StatsResponse getLast_30_days() {
		return last_30_days;
	}
	
	public void setLast_30_days(StatsResponse last_30_days) {
		this.last_30_days = last_30_days;
	}

	public StatsResponse getLast_60_days() {
		return last_60_days;
	}

	public void setLast_60_days(StatsResponse last_60_days) {
		this.last_60_days = last_60_days;
	}
	
	public StatsResponse getLast_90_days() {
		return last_90_days;
	}
	
	public void setLast_90_days(StatsResponse last_90_days) {
		this.last_90_days = last_90_days;
	}
	
	public StatsResponse getAll_time() {
		return all_time;
	}
	
	public void setAll_time(StatsResponse all_time) {
		this.all_time = all_time;
	}
}
