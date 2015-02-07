package com.cribbstechnologies.clients.mandrill.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MandrillRecipient {
	
	String email;
	String name;
    String type;
	
	public MandrillRecipient(String name, String email) {
		this.email = email;
		this.name = name;
	}
	
    /**
     * 
     * @param name
     * @param email
     * @param type
     *            - one of "to", "cc", "bcc". defaults to "to" if not provided
     */
    public MandrillRecipient(String name, String email, String type) {
        this.email = email;
        this.name = name;
        this.type = type;
    }

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public final String getType() {
        return type;
    }

    public final void setType(String type) {
        this.type = type;
    }
	
}
