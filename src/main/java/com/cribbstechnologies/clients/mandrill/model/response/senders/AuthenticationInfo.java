/*
 * AuthenticationInfo.java
 *
 * Created on Apr 27, 2016, 1:07:39 PM
 *
 * Copyright (C) 2014, Blueprint Solutions Inc.
 * All rights reserved.
 */

package com.cribbstechnologies.clients.mandrill.model.response.senders;

/**
 *
 * @author Aleem Sunderji, aleem@bp-solutions.net
 */
public class AuthenticationInfo {

    // Instance variables
    protected boolean valid;
    protected String valid_after;
    protected String error;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getValid_after() {
        return valid_after;
    }

    public void setValid_after(String valid_after) {
        this.valid_after = valid_after;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
