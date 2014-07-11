package com.cribbstechnologies.clients.mandrill.model.response.templates;

import com.cribbstechnologies.clients.mandrill.model.response.BaseMandrillResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TemplateResponse extends BaseMandrillResponse {

	String id;
	String name;
	String code;
	String created_at;
	String updated_at;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCreated_at() {
		return created_at;
	}
	
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	public String getUpdated_at() {
		return updated_at;
	}
	
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
