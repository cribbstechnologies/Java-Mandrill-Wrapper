package com.cribbstechnologies.clients.mandrill.model.response.message;

import com.cribbstechnologies.clients.mandrill.model.response.BaseMandrillResponse;

public class MessageResponse extends BaseMandrillResponse {

	String email; 
	String status;
    String _id;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
