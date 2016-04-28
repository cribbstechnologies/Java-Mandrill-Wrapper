package com.cribbstechnologies.clients.mandrill.model.response.senders;

import com.cribbstechnologies.clients.mandrill.model.response.BaseMandrillResponse;

public class DomainInfoResponse extends BaseMandrillResponse {

    protected String domain;
    protected String created_at;
    protected String last_tested_at;
    protected AuthenticationInfo spf;
    protected AuthenticationInfo dkim;
    protected String verified_at;
    protected boolean valid_signing;
    
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLast_tested_at() {
        return last_tested_at;
    }

    public void setLast_tested_at(String last_tested_at) {
        this.last_tested_at = last_tested_at;
    }

    public String getVerified_at() {
        return verified_at;
    }

    public void setVerified_at(String verified_at) {
        this.verified_at = verified_at;
    }

    public boolean isValid_signing() {
        return valid_signing;
    }

    public void setValid_signing(boolean valid_signing) {
        this.valid_signing = valid_signing;
    }

    public AuthenticationInfo getSpf() {
        return spf;
    }

    public void setSpf(AuthenticationInfo spf) {
        this.spf = spf;
    }

    public AuthenticationInfo getDkim() {
        return dkim;
    }

    public void setDkim(AuthenticationInfo dkim) {
        this.dkim = dkim;
    }

}
