package com.cribbstechnologies.clients.mandrill.request;

import java.util.List;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import com.cribbstechnologies.clients.mandrill.model.BaseMandrillRequest;
import com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithTag;
import com.cribbstechnologies.clients.mandrill.model.ServiceMethods;
import com.cribbstechnologies.clients.mandrill.model.response.BaseMandrillAnonymousListResponse;
import com.cribbstechnologies.clients.mandrill.model.response.tags.BaseTag;
import com.cribbstechnologies.clients.mandrill.model.response.tags.TagListResponse;
import com.cribbstechnologies.clients.mandrill.model.response.tags.TagSeriesResponse;
import com.cribbstechnologies.clients.mandrill.model.response.tags.TagWithTime;
import com.fasterxml.jackson.core.type.TypeReference;

public class MandrillTagsRequest {

    MandrillRESTRequest request;

    TypeReference<List<TagWithTime>> timeTagReference = new TypeReference<List<TagWithTime>>() {
    };
    TypeReference<List<BaseTag>> nameTagReference = new TypeReference<List<BaseTag>>() {
    };

    public TagListResponse getList(BaseMandrillRequest tagsRequest) throws RequestFailedException {
        TagListResponse response = new TagListResponse();
        response.setList(((BaseMandrillAnonymousListResponse<BaseTag>) request.postRequest(tagsRequest, ServiceMethods.Tags.LIST, TagListResponse.class, nameTagReference))
                .getList());
        return response;
    }

    public TagSeriesResponse getTimeSeries(MandrillRequestWithTag tagsRequest) throws RequestFailedException {
        TagSeriesResponse response = new TagSeriesResponse();
        response.setList(((BaseMandrillAnonymousListResponse<TagWithTime>) request.postRequest(tagsRequest, ServiceMethods.Tags.TIME_SERIES, TagSeriesResponse.class,
                timeTagReference)).getList());
        return response;
    }

    public TagSeriesResponse getAllTimeSeries(BaseMandrillRequest tagsRequest) throws RequestFailedException {
        TagSeriesResponse response = new TagSeriesResponse();
        response.setList(((BaseMandrillAnonymousListResponse<TagWithTime>) request.postRequest(tagsRequest, ServiceMethods.Tags.ALL_TIME_SERIES, TagSeriesResponse.class,
                timeTagReference)).getList());
        return response;
    }

    public void setRequest(MandrillRESTRequest request) {
        this.request = request;
    }
}
