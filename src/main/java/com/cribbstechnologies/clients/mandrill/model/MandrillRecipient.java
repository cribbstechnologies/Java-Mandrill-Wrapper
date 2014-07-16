package com.cribbstechnologies.clients.mandrill.model;

public class MandrillRecipient {

    String email;
    String name;
    String type;

    public MandrillRecipient(String name, String email) {
        this.email = email;
        this.name = name;
    }
    
    public MandrillRecipient(String name, String email, String type) {
        this(name, email);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
