package com.cribbstechnologies.clients.mandrill.model;

import java.util.List;

public class MandrillTemplatedMessageRequest extends BaseMandrillRequest {
	
	String template_name;
	List<TemplateContent >template_content;
    List<MergeVar> merge_vars;
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

    public List<MergeVar> getMerge_vars() {
        return merge_vars;
    }public void setMerge_vars(List<MergeVar> merge_vars) {
    this.merge_vars = merge_vars;
}}
