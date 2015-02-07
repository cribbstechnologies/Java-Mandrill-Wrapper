package com.cribbstechnologies.clients.mandrill.model;

public class MandrillAttachment {

    String type;
    String name;
    String content;

    public MandrillAttachment(String type, String name, String content) {
        this.type = type;
        this.name = name;
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    /**
     * The attachment is represented by a base-64-encoded string
     * 
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

}
