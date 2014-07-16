package com.cribbstechnologies.clients.mandrill.model;

/**
 * 
 * @author Martin Zapata
 *
 */

public class Attachment implements java.io.Serializable {
	
	private String type, name, content;
	
	public Attachment(String type, String name, String content) {
		this.type = type;
		this.name = name;
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
