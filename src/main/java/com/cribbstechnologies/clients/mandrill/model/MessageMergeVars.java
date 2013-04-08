package com.cribbstechnologies.clients.mandrill.model;

import java.util.List;

public class MessageMergeVars {
    private String rcpt;
    private List<MergeVar> vars;

    public String getRcpt() {
        return rcpt;
    }

    public void setRcpt(String rcpt) {
        this.rcpt = rcpt;
    }

    public List<MergeVar> getVars() {
        return vars;
    }

    public void setVars(List<MergeVar> vars) {
        this.vars = vars;
    }
}
