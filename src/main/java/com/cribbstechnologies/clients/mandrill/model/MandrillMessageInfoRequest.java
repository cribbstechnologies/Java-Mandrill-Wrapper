package com.cribbstechnologies.clients.mandrill.model;

import com.cribbstechnologies.clients.mandrill.model.BaseMandrillRequest;

/**
 * User: telrod
 * Date: 4/10/14
 */
public class MandrillMessageInfoRequest extends BaseMandrillRequest {

    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
