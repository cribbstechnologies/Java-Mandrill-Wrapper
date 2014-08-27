package com.cribbstechnologies.clients.mandrill.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import com.cribbstechnologies.clients.mandrill.model.BaseMandrillRequest;
import com.cribbstechnologies.clients.mandrill.model.MandrillHtmlMessage;
import com.cribbstechnologies.clients.mandrill.model.MandrillMessageRequest;
import com.cribbstechnologies.clients.mandrill.model.MandrillRecipient;
import com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithDomain;
import com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithQuery;
import com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithTag;
import com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithUrl;
import com.cribbstechnologies.clients.mandrill.model.response.BaseMandrillAnonymousListResponse;
import com.cribbstechnologies.clients.mandrill.model.response.StatsResponse;
import com.cribbstechnologies.clients.mandrill.model.response.message.MessageResponse;
import com.cribbstechnologies.clients.mandrill.model.response.message.SendMessageResponse;
import com.cribbstechnologies.clients.mandrill.model.response.tags.BaseTag;
import com.cribbstechnologies.clients.mandrill.model.response.tags.TagListResponse;
import com.cribbstechnologies.clients.mandrill.model.response.tags.TagSeriesResponse;
import com.cribbstechnologies.clients.mandrill.model.response.tags.TagWithTime;
import com.cribbstechnologies.clients.mandrill.model.response.templates.TemplateResponse;
import com.cribbstechnologies.clients.mandrill.model.response.urls.TimeUrlResponse;
import com.cribbstechnologies.clients.mandrill.model.response.urls.UrlListResponse;
import com.cribbstechnologies.clients.mandrill.model.response.urls.UrlResponse;
import com.cribbstechnologies.clients.mandrill.model.response.urls.UrlTimeResponse;
import com.cribbstechnologies.clients.mandrill.model.response.users.DisableResponse;
import com.cribbstechnologies.clients.mandrill.model.response.users.MandrillSender;
import com.cribbstechnologies.clients.mandrill.model.response.users.UsersInfoResponse;
import com.cribbstechnologies.clients.mandrill.model.response.users.UsersSendersResponse;
import com.cribbstechnologies.clients.mandrill.util.MandrillConfiguration;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MandrillRESTRequestTest {

    MandrillRESTRequest request;
    @Mock
    ObjectMapper mapper;
    @Mock
    HttpClient client;
    @Mock
    ClientConnectionManager manager;
    @Mock
    HttpEntity entity;
    @Mock
    HttpResponse response;
    @Mock
    StatusLine statusLine;
    MandrillConfiguration config = new MandrillConfiguration();

    BaseMandrillRequest emptyBaseRequest = new BaseMandrillRequest();
    BaseMandrillRequest mutableBaseRequest;

    MandrillRequestWithDomain emptyEmailRequest = new MandrillRequestWithDomain();
    MandrillRequestWithDomain mutableEmailRequest = new MandrillRequestWithDomain();

    MandrillRequestWithQuery emptyQueryRequest = new MandrillRequestWithQuery();
    MandrillRequestWithQuery mutableQueryRequest;

    MandrillRequestWithTag emptyTagRequest = new MandrillRequestWithTag();
    MandrillRequestWithTag mutableTagRequest;

    MandrillRequestWithUrl emptyUrlRequest = new MandrillRequestWithUrl();
    MandrillRequestWithUrl mutableUrlRequest;

    MandrillMessageRequest emptyMessageRequest = new MandrillMessageRequest();
    MandrillHtmlMessage emptyMessage;

    MandrillMessageRequest mutableMessageRequest = new MandrillMessageRequest();
    MandrillHtmlMessage mutableMessage;

    @Before
    public void before() {
        initMocks(this);
        this.config.setApiVersion("1.0");
        this.config.setBaseURL("https://mandrillapp.com/api");
    }

    private void initRequestWithActualMapper() {
        this.request = new MandrillRESTRequest();
        this.request.setObjectMapper(new ObjectMapper());
    }

    private void initRequestWithMockedMapper() {
        this.request = new MandrillRESTRequest();
        this.request.setObjectMapper(this.mapper);
    }

    @Test
    public void testDisableResponseConversion() throws IOException {
        this.initRequestWithActualMapper();

        StringWriter sw = new StringWriter();
        IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("users/disableSenderResponse.txt"), sw);
        DisableResponse response = (DisableResponse) this.request.convertResponseData(sw.toString(), DisableResponse.class);

        assertEquals("example domain", response.getDomain());
        assertEquals("example created_at", response.getCreated_at());
        assertEquals("example approved_at", response.getApproved_at());
        assertTrue(response.isIs_enabled());
    }

    @Test
    public void testGetPostDataBaseMandrillRequest() throws Exception {
        this.initRequestWithActualMapper();

        assertEquals("{\"key\":null}", this.request.getPostData(this.emptyBaseRequest));
        this.mutableBaseRequest = new BaseMandrillRequest();
        this.mutableBaseRequest.setKey("this is my key");
        assertEquals("{\"key\":\"this is my key\"}", this.request.getPostData(this.mutableBaseRequest));

        this.mutableBaseRequest.setKey("this is my key with \"extra\" quotes");
        assertEquals("{\"key\":\"this is my key with \\\"extra\\\" quotes\"}", this.request.getPostData(this.mutableBaseRequest));

    }

    @Test
    public void testGetPostDataJsonGenerationException() throws Exception {
        this.initRequestWithMockedMapper();

        Mockito.when(this.mapper.writeValueAsString(this.emptyBaseRequest)).thenThrow(new JsonGenerationException("Mockito!"));
        try {
            this.request.getPostData(this.emptyBaseRequest);
            fail("Exception not thrown");
        } catch (JsonGenerationException jge) {
            assertEquals("Mockito!", jge.getMessage());
        }
    }

    @Test
    public void testGetPostDataJsonMappingException() throws Exception {
        this.initRequestWithMockedMapper();

        Mockito.when(this.mapper.writeValueAsString(this.emptyBaseRequest)).thenThrow(new JsonMappingException("Mockito!"));
        try {
            this.request.getPostData(this.emptyBaseRequest);
        } catch (JsonMappingException jme) {
            assertEquals("Mockito!", jme.getMessage());
        }
    }

    @Test
    public void testGetPostDataMandrillMessageRequest() throws Exception {
        this.initRequestWithActualMapper();

        this.emptyMessageRequest.setMessage(this.emptyMessage);
        assertEquals("{\"key\":null,\"message\":null}", this.request.getPostData(this.emptyMessageRequest));

        this.mutableMessageRequest = new MandrillMessageRequest();
        this.mutableMessageRequest.setKey("API Key");
        this.mutableMessage = new MandrillHtmlMessage();
        this.mutableMessage.setHtml("Test html");
        this.mutableMessage.setText("Test text");
        this.mutableMessage.setSubject("Test subject");
        this.mutableMessage.setFrom_email("from@email.com");
        this.mutableMessage.setFrom_name("From Name");
        this.mutableMessage.setSubaccount("test");
        MandrillRecipient[] to = new MandrillRecipient[2];
        to[0] = new MandrillRecipient("to1", "to1");
        to[1] = new MandrillRecipient("to2", "to2");
        this.mutableMessage.setTo(to);
        this.mutableMessage.setTrack_opens(false);
        this.mutableMessage.setTrack_clicks(true);
        String[] tags = new String[2];
        tags[0] = "tag1";
        tags[1] = "tag2";
        this.mutableMessage.setTags(tags);
        Map<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("headerName", "headerValue");

        this.mutableMessage.setHeaders(headerMap);

        this.mutableMessageRequest.setMessage(this.mutableMessage);
        // System.out.println(request.getPostData(mutableMessageRequest));
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("\"key\":\"API Key\"");
        sb.append(",\"message\":{");
        sb.append("\"text\":\"Test text\"");
        sb.append(",\"subject\":\"Test subject\"");
        sb.append(",\"from_email\":\"from@email.com\"");
        sb.append(",\"from_name\":\"From Name\"");
        sb.append(",\"subaccount\":\"test\"");
        sb.append(",\"to\":[{\"email\":\"to1\",\"name\":\"to1\"},{\"email\":\"to2\",\"name\":\"to2\"}]");
        sb.append(",\"track_opens\":false");
        sb.append(",\"track_clicks\":true");
        sb.append(",\"auto_text\":false");
        sb.append(",\"url_strip_qs\":false");
        sb.append(",\"preserve_recipients\":false");
        sb.append(",\"tags\":[\"tag1\",\"tag2\"]");
        sb.append(",\"google_analytics_domains\":[]");
        sb.append(",\"google_analytics_campaign\":[]");
        sb.append(",\"global_merge_vars\":null");
        sb.append(",\"merge_vars\":null");
        sb.append(",\"attachments\":null");
        sb.append(",\"headers\":{\"headerName\":\"headerValue\"},");
        sb.append("\"html\":\"Test html\"");
        sb.append("}}");
        String output = this.request.getPostData(this.mutableMessageRequest);
        System.out.println("Comparing:\n" + sb.toString() + "\n" + output);
        assertEquals(sb.toString(), output);
    }

    @Test
    public void testGetPostDataMandrillRequestWithEmail() throws Exception {
        this.initRequestWithActualMapper();

        assertEquals("{\"key\":null,\"domain\":null}", this.request.getPostData(this.emptyEmailRequest));
        this.mutableEmailRequest = new MandrillRequestWithDomain();
        this.mutableEmailRequest.setKey("12345");
        this.mutableEmailRequest.setDomain("email@email.com");
        assertEquals("{\"key\":\"12345\",\"domain\":\"email@email.com\"}", this.request.getPostData(this.mutableEmailRequest));
    }

    @Test
    public void testGetPostDataMandrillRequestWithQuery() throws Exception {
        this.initRequestWithActualMapper();

        assertEquals("{\"key\":null,\"q\":null}", this.request.getPostData(this.emptyQueryRequest));
        this.mutableQueryRequest = new MandrillRequestWithQuery();
        this.mutableQueryRequest.setKey("7890");
        this.mutableQueryRequest.setQ("query string");
        assertEquals("{\"key\":\"7890\",\"q\":\"query string\"}", this.request.getPostData(this.mutableQueryRequest));
    }

    @Test
    public void testGetPostDataMandrillRequestWithTag() throws Exception {
        this.initRequestWithActualMapper();

        assertEquals("{\"key\":null,\"tag\":null}", this.request.getPostData(this.emptyTagRequest));
        this.mutableTagRequest = new MandrillRequestWithTag();
        this.mutableTagRequest.setKey("ABC");
        this.mutableTagRequest.setTag("Tag, you're it");
        assertEquals("{\"key\":\"ABC\",\"tag\":\"Tag, you're it\"}", this.request.getPostData(this.mutableTagRequest));
    }

    @Test
    public void testGetPostDataMandrillRequestWithUrl() throws Exception {
        this.initRequestWithActualMapper();

        assertEquals("{\"key\":null,\"url\":null}", this.request.getPostData(this.emptyUrlRequest));
        this.mutableUrlRequest = new MandrillRequestWithUrl();
        this.mutableUrlRequest.setKey("TEST");
        this.mutableUrlRequest.setUrl("http://www.google.com");
        assertEquals("{\"key\":\"TEST\",\"url\":\"http://www.google.com\"}", this.request.getPostData(this.mutableUrlRequest));
    }

    @Test
    public void testListTagsResponseConversion() throws IOException {
        this.initRequestWithActualMapper();

        StringWriter sw = new StringWriter();
        IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("tags/listResponse.txt"), sw);
        TypeReference tagListReference = new TypeReference<List<BaseTag>>() {
        };
        BaseMandrillAnonymousListResponse<BaseTag> response = (BaseMandrillAnonymousListResponse<BaseTag>) this.request.convertAnonymousListResponseData(sw.toString(),
                TagListResponse.class, tagListReference);

        assertEquals(2, response.getList().size());

        BaseTag tag = response.getList().get(0);
        assertEquals("example tag1", tag.getTag());
        assertEquals(1, tag.getSent());
        assertEquals(2, tag.getHard_bounces());
        assertEquals(3, tag.getSoft_bounces());
        assertEquals(4, tag.getRejects());
        assertEquals(5, tag.getComplaints());
        assertEquals(6, tag.getUnsubs());
        assertEquals(7, tag.getOpens());
        assertEquals(8, tag.getClicks());

        tag = response.getList().get(1);
        assertEquals("example tag11", tag.getTag());
        assertEquals(11, tag.getSent());
        assertEquals(12, tag.getHard_bounces());
        assertEquals(13, tag.getSoft_bounces());
        assertEquals(14, tag.getRejects());
        assertEquals(15, tag.getComplaints());
        assertEquals(16, tag.getUnsubs());
        assertEquals(17, tag.getOpens());
        assertEquals(18, tag.getClicks());
    }

    @Test
    public void testPostRequest() throws ClientProtocolException, IOException {
        this.request = new MandrillRESTRequest();
        this.request.setHttpClient(this.client);
        this.request.setConfig(this.config);
        this.request.setObjectMapper(new ObjectMapper());

        doThrow(new MalformedURLException("Mockito!")).when(this.client).execute(isA(HttpPost.class));
        try {
            this.request.postRequest(this.emptyBaseRequest, "test", null);
            fail("Exception not thrown");
        } catch (RequestFailedException e) {
            assertEquals("Malformed url", e.getMessage());
        }

        doThrow(new IOException("Mockito!")).when(this.client).execute(isA(HttpPost.class));
        try {
            this.request.postRequest(this.emptyBaseRequest, "test", null);
            fail("Exception not thrown");
        } catch (RequestFailedException e) {
            assertEquals("IOException", e.getMessage());
        }
    }

    @Test
    public void testPostRequestMapperExceptions() throws ClientProtocolException, IOException {
        this.request = new MandrillRESTRequest();
        this.request.setHttpClient(this.client);
        this.request.setConfig(this.config);
        this.request.setObjectMapper(this.mapper);

        doThrow(new JsonGenerationException("Mockito!")).when(this.mapper).writeValueAsString(isA(BaseMandrillRequest.class));
        try {
            this.request.postRequest(this.emptyBaseRequest, "test", null);
            fail("Exception not thrown");
        } catch (RequestFailedException e) {
            assertEquals("Json Generation Exception", e.getMessage());
        }

        doThrow(new JsonMappingException("Mockito!")).when(this.mapper).writeValueAsString(isA(BaseMandrillRequest.class));
        try {
            this.request.postRequest(this.emptyBaseRequest, "test", null);
            fail("Exception not thrown");
        } catch (RequestFailedException e) {
            assertEquals("Json Mapping Exception", e.getMessage());
        }
    }

    @Test
    public void testPostRequestNon200Response() {
        try {
            this.request = new MandrillRESTRequest();
            this.request.setHttpClient(this.client);
            this.request.setConfig(this.config);
            this.request.setObjectMapper(this.mapper);

            doReturn("postData").when(this.mapper).writeValueAsString(this.emptyBaseRequest);
            doReturn(this.response).when(this.client).execute(isA(HttpPost.class));
            doReturn(this.manager).when(this.client).getConnectionManager();
            Mockito.when(this.response.getEntity()).thenReturn(this.entity);
            InputStream inputStream = IOUtils.toInputStream("INPUT");
            Mockito.when(this.entity.getContent()).thenReturn(inputStream);
            Mockito.when(this.response.getStatusLine()).thenReturn(this.statusLine);
            Mockito.when(this.statusLine.getStatusCode()).thenReturn(500);

            this.request.postRequest(this.emptyBaseRequest, "Foo", null);
        } catch (RequestFailedException rfe) {
            assertEquals("Failed : HTTP error code : 500 INPUT", rfe.getMessage());
        } catch (ClientProtocolException e) {
            fail("Mockito is a good mocking framework, this shouldn't happen");
        } catch (IOException e) {
            fail("Mockito is a good mocking framework, this shouldn't happen");
        }
    }

    @Test
    public void testSendersResponseConversion() throws IOException {
        this.initRequestWithActualMapper();

        StringWriter sw = new StringWriter();
        IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("users/sendersResponse.txt"), sw);
        TypeReference usersListReference = new TypeReference<List<MandrillSender>>() {
        };
        BaseMandrillAnonymousListResponse<MandrillSender> response = (BaseMandrillAnonymousListResponse<MandrillSender>) this.request.convertAnonymousListResponseData(
                sw.toString(), UsersSendersResponse.class, usersListReference);

        assertEquals(2, response.getList().size());

        MandrillSender sender = response.getList().get(0);
        assertEquals("example address", sender.getAddress());
        assertEquals("example created_at", sender.getCreated_at());
        assertTrue(sender.getIs_enabled());

        sender = response.getList().get(1);
        assertEquals("example address2", sender.getAddress());
        assertEquals("example created_at2", sender.getCreated_at());
        assertFalse(sender.getIs_enabled());
    }

    @Test
    public void testSendMessageResponseConversion() throws IOException {
        this.initRequestWithActualMapper();

        StringWriter sw = new StringWriter();
        IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("messages/sendMessageResponse.txt"), sw);
        TypeReference responseListReference = new TypeReference<List<MessageResponse>>() {
        };
        BaseMandrillAnonymousListResponse<MessageResponse> response = (BaseMandrillAnonymousListResponse<MessageResponse>) this.request.convertAnonymousListResponseData(
                sw.toString(), SendMessageResponse.class, responseListReference);

        assertEquals(2, response.getList().size());

        MessageResponse resp = response.getList().get(0);
        assertEquals("example email", resp.getEmail());
        assertEquals("example status", resp.getStatus());

        resp = response.getList().get(1);
        assertEquals("example email2", resp.getEmail());
        assertEquals("example status2", resp.getStatus());
    }

    @Test
    public void testTemplateListResponseConversion() throws IOException {
        this.initRequestWithActualMapper();

        StringWriter sw = new StringWriter();
        IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("templates/templatesListResponse.txt"), sw);
        TypeReference templatesListReference = new TypeReference<List<TemplateResponse>>() {
        };
        BaseMandrillAnonymousListResponse<TemplateResponse> response = (BaseMandrillAnonymousListResponse<TemplateResponse>) this.request.convertAnonymousListResponseData(
                sw.toString(), TemplateResponse.class, templatesListReference);

        assertEquals(2, response.getList().size());
        TemplateResponse tr = response.getList().get(0);
        assertEquals("example name1", tr.getName());
        assertEquals("example code1", tr.getCode());
        assertEquals("example created_at1", tr.getCreated_at());
        assertEquals("example updated_at1", tr.getUpdated_at());

        tr = response.getList().get(1);
        assertEquals("example name11", tr.getName());
        assertEquals("example code11", tr.getCode());
        assertEquals("example created_at11", tr.getCreated_at());
        assertEquals("example updated_at11", tr.getUpdated_at());
    }

    @Test
    public void testTemplateResponseConversion() throws IOException {
        this.initRequestWithActualMapper();

        StringWriter sw = new StringWriter();
        IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("templates/templateResponse.txt"), sw);

        TemplateResponse response = (TemplateResponse) this.request.convertResponseData(sw.toString(), TemplateResponse.class);

        assertEquals("example name", response.getName());
        assertEquals("example code", response.getCode());
        assertEquals("example created_at", response.getCreated_at());
        assertEquals("example updated_at", response.getUpdated_at());
    }

    @Test
    public void testTimeTagsResponseConversion() throws IOException {
        this.initRequestWithActualMapper();

        StringWriter sw = new StringWriter();
        IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("tags/timeSeriesResponse.txt"), sw);
        TypeReference tagListReference = new TypeReference<List<TagWithTime>>() {
        };
        BaseMandrillAnonymousListResponse<TagWithTime> response = (BaseMandrillAnonymousListResponse<TagWithTime>) this.request.convertAnonymousListResponseData(sw.toString(),
                TagSeriesResponse.class, tagListReference);

        assertEquals(2, response.getList().size());

        TagWithTime tag = response.getList().get(0);
        assertEquals("example time1", tag.getTime());
        assertEquals(2, tag.getSent());
        assertEquals(3, tag.getHard_bounces());
        assertEquals(4, tag.getSoft_bounces());
        assertEquals(5, tag.getRejects());
        assertEquals(6, tag.getComplaints());
        assertEquals(7, tag.getOpens());
        assertEquals(8, tag.getUnique_opens());
        assertEquals(9, tag.getClicks());
        assertEquals(10, tag.getUnique_clicks());

        tag = response.getList().get(1);
        assertEquals("example time11", tag.getTime());
        assertEquals(12, tag.getSent());
        assertEquals(13, tag.getHard_bounces());
        assertEquals(14, tag.getSoft_bounces());
        assertEquals(15, tag.getRejects());
        assertEquals(16, tag.getComplaints());
        assertEquals(17, tag.getOpens());
        assertEquals(18, tag.getUnique_opens());
        assertEquals(19, tag.getClicks());
        assertEquals(110, tag.getUnique_clicks());
    }

    @Test
    public void testUrlListResponseConversion() throws IOException {
        this.initRequestWithActualMapper();

        StringWriter sw = new StringWriter();
        IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("urls/urlList.txt"), sw);
        TypeReference urlListReference = new TypeReference<List<UrlResponse>>() {
        };
        BaseMandrillAnonymousListResponse<UrlResponse> response = (BaseMandrillAnonymousListResponse<UrlResponse>) this.request.convertAnonymousListResponseData(sw.toString(),
                UrlListResponse.class, urlListReference);

        assertEquals(2, response.getList().size());

        UrlResponse urlResponse = response.getList().get(0);
        assertEquals("example url1", urlResponse.getUrl());
        assertEquals(2, urlResponse.getSent());
        assertEquals(3, urlResponse.getClicks());
        assertEquals(4, urlResponse.getUnique_clicks());

        urlResponse = response.getList().get(1);
        assertEquals("example url11", urlResponse.getUrl());
        assertEquals(12, urlResponse.getSent());
        assertEquals(13, urlResponse.getClicks());
        assertEquals(14, urlResponse.getUnique_clicks());
    }

    @Test
    public void testUrlTimeResponseConversion() throws IOException {
        this.initRequestWithActualMapper();

        StringWriter sw = new StringWriter();
        IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("urls/urlTimeResponse.txt"), sw);
        TypeReference urlTimeReference = new TypeReference<List<TimeUrlResponse>>() {
        };
        BaseMandrillAnonymousListResponse<TimeUrlResponse> response = (BaseMandrillAnonymousListResponse<TimeUrlResponse>) this.request.convertAnonymousListResponseData(
                sw.toString(), UrlTimeResponse.class, urlTimeReference);

        assertEquals(2, response.getList().size());

        TimeUrlResponse timeUrlResponse = response.getList().get(0);
        assertEquals("example time1", timeUrlResponse.getTime());
        assertEquals(2, timeUrlResponse.getSent());
        assertEquals(3, timeUrlResponse.getClicks());
        assertEquals(4, timeUrlResponse.getUnique_clicks());

        timeUrlResponse = response.getList().get(1);
        assertEquals("example time11", timeUrlResponse.getTime());
        assertEquals(12, timeUrlResponse.getSent());
        assertEquals(13, timeUrlResponse.getClicks());
        assertEquals(14, timeUrlResponse.getUnique_clicks());
    }

    @Test
    public void testUsersInfoResponseConversion() throws JsonParseException, JsonMappingException, IOException {
        this.initRequestWithActualMapper();

        StringWriter sw = new StringWriter();
        IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("users/infoResponse.txt"), sw);
        UsersInfoResponse myResponse = (UsersInfoResponse) this.request.convertResponseData(sw.toString(), UsersInfoResponse.class);

        assertEquals("example username", myResponse.getUsername());
        assertEquals("example created_at", myResponse.getCreated_at());
        assertEquals(42, myResponse.getReputation());
        assertEquals(32, myResponse.getHourly_quota());

        StatsResponse today = myResponse.getStats().getToday();
        assertEquals(1, today.getSent());
        assertEquals(2, today.getHard_bounces());
        assertEquals(3, today.getSoft_bounces());
        assertEquals(4, today.getRejects());
        assertEquals(5, today.getComplaints());
        assertEquals(6, today.getUnsubs());
        assertEquals(7, today.getOpens());
        assertEquals(8, today.getUnique_opens());
        assertEquals(9, today.getClicks());
        assertEquals(10, today.getUnique_clicks());

        StatsResponse last7 = myResponse.getStats().getLast_7_days();
        assertEquals(11, last7.getSent());
        assertEquals(12, last7.getHard_bounces());
        assertEquals(13, last7.getSoft_bounces());
        assertEquals(14, last7.getRejects());
        assertEquals(15, last7.getComplaints());
        assertEquals(16, last7.getUnsubs());
        assertEquals(17, last7.getOpens());
        assertEquals(18, last7.getUnique_opens());
        assertEquals(19, last7.getClicks());
        assertEquals(20, last7.getUnique_clicks());

        StatsResponse last30 = myResponse.getStats().getLast_30_days();
        assertEquals(21, last30.getSent());
        assertEquals(22, last30.getHard_bounces());
        assertEquals(23, last30.getSoft_bounces());
        assertEquals(24, last30.getRejects());
        assertEquals(25, last30.getComplaints());
        assertEquals(26, last30.getUnsubs());
        assertEquals(27, last30.getOpens());
        assertEquals(28, last30.getUnique_opens());
        assertEquals(29, last30.getClicks());
        assertEquals(30, last30.getUnique_clicks());

        StatsResponse last60 = myResponse.getStats().getLast_60_days();
        assertEquals(31, last60.getSent());
        assertEquals(32, last60.getHard_bounces());
        assertEquals(33, last60.getSoft_bounces());
        assertEquals(34, last60.getRejects());
        assertEquals(35, last60.getComplaints());
        assertEquals(36, last60.getUnsubs());
        assertEquals(37, last60.getOpens());
        assertEquals(38, last60.getUnique_opens());
        assertEquals(39, last60.getClicks());
        assertEquals(40, last60.getUnique_clicks());

        StatsResponse last90 = myResponse.getStats().getLast_90_days();
        assertEquals(41, last90.getSent());
        assertEquals(42, last90.getHard_bounces());
        assertEquals(43, last90.getSoft_bounces());
        assertEquals(44, last90.getRejects());
        assertEquals(45, last90.getComplaints());
        assertEquals(46, last90.getUnsubs());
        assertEquals(47, last90.getOpens());
        assertEquals(48, last90.getUnique_opens());
        assertEquals(49, last90.getClicks());
        assertEquals(50, last90.getUnique_clicks());

        StatsResponse allTime = myResponse.getStats().getAll_time();
        assertEquals(51, allTime.getSent());
        assertEquals(52, allTime.getHard_bounces());
        assertEquals(53, allTime.getSoft_bounces());
        assertEquals(54, allTime.getRejects());
        assertEquals(55, allTime.getComplaints());
        assertEquals(56, allTime.getUnsubs());
        assertEquals(57, allTime.getOpens());
        assertEquals(58, allTime.getUnique_opens());
        assertEquals(59, allTime.getClicks());
        assertEquals(60, allTime.getUnique_clicks());
    }

    @Test
    public void testVerifySenderResponseConversion() throws IOException {
        this.initRequestWithActualMapper();

        StringWriter sw = new StringWriter();
        IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("users/verifySenderResponse.txt"), sw);
        DisableResponse response = (DisableResponse) this.request.convertResponseData(sw.toString(), DisableResponse.class);

        assertEquals("example domain", response.getDomain());
        assertEquals("example created_at", response.getCreated_at());
        assertEquals("example approved_at", response.getApproved_at());
        assertTrue(response.isIs_enabled());
    }
}