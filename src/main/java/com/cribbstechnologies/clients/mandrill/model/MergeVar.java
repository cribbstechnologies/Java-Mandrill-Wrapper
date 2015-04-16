package com.cribbstechnologies.clients.mandrill.model;

/**
 * Created with IntelliJ IDEA.
 * User: azuercher
 * Date: 6/27/12
 * Time: 5:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class MergeVar {
    private String name;
    private Object content;
    public MergeVar(String name, Object content) {
        this.name = name;
        this.content = content;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
