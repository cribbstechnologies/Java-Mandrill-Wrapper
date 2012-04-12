package com.cribbstechnologies.clients.mandrill.it;

import static org.junit.Assert.fail;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import com.cribbstechnologies.clients.mandrill.model.BaseMandrillRequest;
import com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithTag;
import com.cribbstechnologies.clients.mandrill.model.response.tags.TagListResponse;
import com.cribbstechnologies.clients.mandrill.model.response.tags.TagSeriesResponse;
import com.cribbstechnologies.clients.mandrill.request.MandrillRESTRequest;
import com.cribbstechnologies.clients.mandrill.request.MandrillTagsRequest;
import com.cribbstechnologies.clients.mandrill.util.MandrillConfiguration;

public class TagsTest {
	
	private static MandrillRESTRequest request = new MandrillRESTRequest();
	private static MandrillConfiguration config = new MandrillConfiguration();
	private static MandrillTagsRequest tagsRequest = new MandrillTagsRequest();
	private static HttpClient client;
	private static ObjectMapper mapper = new ObjectMapper();
	
	@BeforeClass
	public static void beforeClass() {
		config.setApiKey("a2d9f0f9-5646-4af2-a507-76758c71631b");
		config.setApiVersion("1.0");
		config.setBaseURL("https://mandrillapp.com/api");
		request.setConfig(config);
		request.setObjectMapper(mapper);
		tagsRequest.setRequest(request);
	}
	
	@Before 
	public void before() {
		client = new DefaultHttpClient();
		request.setHttpClient(client);
	}
	
	@Test
	public void testGetList() {
		BaseMandrillRequest request = new BaseMandrillRequest();
		try {
			TagListResponse response = tagsRequest.getList(request);
		} catch (RequestFailedException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetAllTimeSeries() {
		BaseMandrillRequest request = new BaseMandrillRequest();
		try {
			TagSeriesResponse response = tagsRequest.getAllTimeSeries(request);
		} catch (RequestFailedException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetTimeSeries() {
		MandrillRequestWithTag request = new MandrillRequestWithTag();
		request.setTag("tag1");
		try {
			TagSeriesResponse response = tagsRequest.getTimeSeries(request);
		} catch (RequestFailedException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
