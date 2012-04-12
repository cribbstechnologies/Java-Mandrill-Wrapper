package com.cribbstechnologies.clients.mandrill.model;

import java.util.List;

public class MandrillTemplatedMessageRequest extends BaseMandrillRequest {
	
	String template_name;
	List<TemplateContent >template_content;
	MandrillMessage message;
	
	public String getTemplate_name() {
		return template_name;
	}
	
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}
	
	public List<TemplateContent> getTemplate_content() {
		return template_content;
	}
	
	public void setTemplate_content(List<TemplateContent> template_content) {
		this.template_content = template_content;
	}

	public MandrillMessage getMessage() {
		return message;
	}

	public void setMessage(MandrillMessage message) {
		this.message = message;
	}

}
