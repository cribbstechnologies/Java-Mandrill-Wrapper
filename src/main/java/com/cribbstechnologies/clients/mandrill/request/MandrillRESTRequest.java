package com.cribbstechnologies.clients.mandrill.request;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import com.cribbstechnologies.clients.mandrill.model.BaseMandrillRequest;
import com.cribbstechnologies.clients.mandrill.model.MandrillError;
import com.cribbstechnologies.clients.mandrill.model.ServiceMethods;
import com.cribbstechnologies.clients.mandrill.model.response.BaseMandrillAnonymousListResponse;
import com.cribbstechnologies.clients.mandrill.model.response.BaseMandrillResponse;
import com.cribbstechnologies.clients.mandrill.model.response.BaseMandrillStringResponse;
import com.cribbstechnologies.clients.mandrill.util.MandrillConfiguration;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

public class MandrillRESTRequest {

    private MandrillConfiguration config;
    private HttpClient httpClient;
    private ObjectMapper objectMapper;

    public BaseMandrillResponse postRequest(BaseMandrillRequest request, String serviceMethod, Object responseClass) throws RequestFailedException {
        return performPostRequest(request, serviceMethod, responseClass, null);
    }

    public BaseMandrillResponse postRequest(BaseMandrillRequest request, String serviceMethod, Object responseClass, TypeReference reference) throws RequestFailedException {
        return performPostRequest(request, serviceMethod, responseClass, reference);
    }

    private BaseMandrillResponse performPostRequest(BaseMandrillRequest request, String serviceMethod, Object responseClass, TypeReference reference) throws RequestFailedException {
        try {
            request.setKey(config.getApiKey());
            HttpPost postRequest = new HttpPost(config.getServiceUrl() + serviceMethod);
            String postData = getPostData(request);
            StringEntity input = new StringEntity(postData, "UTF-8");
            input.setContentType("application/json");
            postRequest.setEntity(input);

            HttpResponse response = httpClient.execute(postRequest);


            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            StringBuffer sb = new StringBuffer();
            String output;
            // System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                sb.append(output);
                // System.out.println(output);
            }

            String responseString = sb.toString();
            EntityUtils.consume(response.getEntity());
            if (response.getStatusLine().getStatusCode() != 200) {
                //throw new RequestFailedException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode() + " " + responseString);
                throw new RequestFailedException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode() + " " + responseString, objectMapper.readValue(responseString, MandrillError.class));
            }

            // for whatever reason the ping response isn't well-formed
            if (ServiceMethods.Users.PING.equals(serviceMethod) && responseString.indexOf("PONG!") > -1) {
                return new BaseMandrillStringResponse(responseString);
            }

            if (reference == null) {
                return convertResponseData(responseString, responseClass);
            } else {
                return convertAnonymousListResponseData(responseString, responseClass, reference);
            }
        } catch (MalformedURLException mURLE) {
            throw new RequestFailedException("Malformed url", mURLE);
        } catch (JsonGenerationException jge) {
            throw new RequestFailedException("Json Generation Exception", jge);
        } catch (JsonMappingException jme) {
            throw new RequestFailedException("Json Mapping Exception", jme);
        } catch (IOException ioe) {
            throw new RequestFailedException("IOException", ioe);
        }
    }

    /**
     * This method will use the Jackson ObjectMapper to generate Mandrill API compatible JSON
     * 
     * @param request
     *            one of @see com.cribbstechnologies.clients.mandrill.model.MandrillMessageRequest, @see
     *            com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithEmail, @see
     *            com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithQuery, @see
     *            com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithTag, @see
     *            com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithUrl
     * @return a JSON @see java.lang.String
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonGenerationException
     */
    protected String getPostData(BaseMandrillRequest request) throws JsonGenerationException, JsonMappingException, IOException {
        return objectMapper.writeValueAsString(request);
    }

    protected BaseMandrillResponse convertResponseData(String response, Object responseClass) throws JsonParseException, JsonMappingException, IOException {
        return objectMapper.readValue(response, (Class<BaseMandrillResponse>) responseClass);
    }

    protected BaseMandrillResponse convertAnonymousListResponseData(String json, Object responseClass, TypeReference reference) throws JsonParseException, JsonMappingException,
            IOException {
        BaseMandrillAnonymousListResponse<BaseMandrillResponse> response = new BaseMandrillAnonymousListResponse<BaseMandrillResponse>();
        List<BaseMandrillResponse> objectList = objectMapper.readValue(json, reference);
        response.setList(objectList);
        return response;
    }

    public void setConfig(MandrillConfiguration config) {
        this.config = config;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

}
