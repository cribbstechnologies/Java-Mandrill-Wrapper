package com.cribbstechnologies.clients.mandrill.model.response.users;

import org.codehaus.jackson.annotate.JsonProperty;

import com.cribbstechnologies.clients.mandrill.model.response.BaseMandrillResponse;

public class PingResponse extends BaseMandrillResponse {

    @JsonProperty("PING")
    public String pingResponse;

    public String getPingResponse() {
        return pingResponse;
    }

    public void setPingResponse(String pingResponse) {
        this.pingResponse = pingResponse;
    }

}
