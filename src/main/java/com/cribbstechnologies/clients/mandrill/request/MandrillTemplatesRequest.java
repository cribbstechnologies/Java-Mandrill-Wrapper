package com.cribbstechnologies.clients.mandrill.request;

import java.util.List;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import com.cribbstechnologies.clients.mandrill.model.BaseMandrillRequest;
import com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithCode;
import com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithName;
import com.cribbstechnologies.clients.mandrill.model.ServiceMethods;
import com.cribbstechnologies.clients.mandrill.model.response.BaseMandrillAnonymousListResponse;
import com.cribbstechnologies.clients.mandrill.model.response.templates.TemplateListResponse;
import com.cribbstechnologies.clients.mandrill.model.response.templates.TemplateResponse;
import com.fasterxml.jackson.core.type.TypeReference;

public class MandrillTemplatesRequest {

    MandrillRESTRequest request;

    TypeReference<List<TemplateResponse>> templatesListReference = new TypeReference<List<TemplateResponse>>() {
    };

    public TemplateResponse addTemplate(MandrillRequestWithCode addRequest) throws RequestFailedException {
        return (TemplateResponse) request.postRequest(addRequest, ServiceMethods.Templates.ADD, TemplateResponse.class);
    }

    public TemplateResponse getTemplateInfo(MandrillRequestWithName infoRequest) throws RequestFailedException {
        return (TemplateResponse) request.postRequest(infoRequest, ServiceMethods.Templates.INFO, TemplateResponse.class);
    }

    public TemplateResponse updateTemplate(MandrillRequestWithCode updateRequest) throws RequestFailedException {
        return (TemplateResponse) request.postRequest(updateRequest, ServiceMethods.Templates.UPDATE, TemplateResponse.class);
    }

    public TemplateResponse deleteTemplate(MandrillRequestWithName deleteRequest) throws RequestFailedException {
        return (TemplateResponse) request.postRequest(deleteRequest, ServiceMethods.Templates.DELETE, TemplateResponse.class);
    }

    public TemplateListResponse getTemplates(BaseMandrillRequest listRequest) throws RequestFailedException {
        TemplateListResponse response = new TemplateListResponse();
        response.setList(((BaseMandrillAnonymousListResponse<TemplateResponse>) request.postRequest(listRequest, ServiceMethods.Templates.LIST, TemplateResponse.class,
                templatesListReference)).getList());
        return response;
    }

    public void setRequest(MandrillRESTRequest request) {
        this.request = request;
    }

}
