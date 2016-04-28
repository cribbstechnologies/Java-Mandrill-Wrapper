package com.cribbstechnologies.clients.mandrill.request;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithDomain;
import com.cribbstechnologies.clients.mandrill.model.ServiceMethods;
import com.cribbstechnologies.clients.mandrill.model.response.BaseMandrillResponse;
import com.cribbstechnologies.clients.mandrill.model.response.senders.DomainInfoResponse;

/**
 * 
 * This class holds functions provided by the Mandrill Senders API
 * 
 * @author Aleem Sunderji, aleem@bp-solutions.net
 * 
 */
public class MandrillSendersRequest {

    protected MandrillRESTRequest request;

    /**
     * Return information about a specific sending domain
     * 
     * @param domainRequest a populated @see com.cribbstechnologies.clients.mandrill.model.BaseMandrillRequest
     * @throws RequestFailedException
     */
    public DomainInfoResponse checkDomain(MandrillRequestWithDomain domainRequest) throws RequestFailedException {
        BaseMandrillResponse response = request.postRequest(domainRequest, ServiceMethods.Senders.CHECK_DOMAIN, DomainInfoResponse.class);
        return (DomainInfoResponse)response;
    }

    public void setRequest(MandrillRESTRequest request) {
        this.request = request;
    }

}
