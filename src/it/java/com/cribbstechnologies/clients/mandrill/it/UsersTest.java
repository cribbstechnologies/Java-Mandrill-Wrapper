package com.cribbstechnologies.clients.mandrill.it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import com.cribbstechnologies.clients.mandrill.model.BaseMandrillRequest;
import com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithDomain;
import com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithEmail;
import com.cribbstechnologies.clients.mandrill.model.response.BaseMandrillStringResponse;
import com.cribbstechnologies.clients.mandrill.model.response.users.DisableResponse;
import com.cribbstechnologies.clients.mandrill.model.response.users.UsersInfoResponse;
import com.cribbstechnologies.clients.mandrill.model.response.users.UsersSendersResponse;
import com.cribbstechnologies.clients.mandrill.model.response.users.VerifyResponse;
import com.cribbstechnologies.clients.mandrill.request.MandrillRESTRequest;
import com.cribbstechnologies.clients.mandrill.request.MandrillUsersRequest;
import com.cribbstechnologies.clients.mandrill.util.MandrillConfiguration;

public class UsersTest {

	private static MandrillRESTRequest request = new MandrillRESTRequest();
	private static MandrillConfiguration config = new MandrillConfiguration();
	private static MandrillUsersRequest usersRequest = new MandrillUsersRequest();
	private static HttpClient client;
	private static ObjectMapper mapper = new ObjectMapper();
	
	@BeforeClass
	public static void beforeClass() {
		config.setApiKey("a2d9f0f9-5646-4af2-a507-76758c71631b");
		config.setApiVersion("1.0");
		config.setBaseURL("https://mandrillapp.com/api");
		request.setConfig(config);
		request.setObjectMapper(mapper);
		usersRequest.setRequest(request);
	}
	
	@Before
	public void before() {
		client = new DefaultHttpClient();
		request.setHttpClient(client);
	}
	
	@Test
	public void testPing() {
		BaseMandrillRequest baseRequest = new BaseMandrillRequest();
		try {
			BaseMandrillStringResponse response = usersRequest.performPing(baseRequest);
			assertEquals("\"PONG!\"", response.getResponse());
		} catch (RequestFailedException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetInfo() {
		BaseMandrillRequest baseRequest = new BaseMandrillRequest();
		try {
			UsersInfoResponse response = usersRequest.getInfo(baseRequest);
		} catch (RequestFailedException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetSenders() {
		BaseMandrillRequest baseRequest = new BaseMandrillRequest();
		try {
			UsersSendersResponse response = usersRequest.getSenders(baseRequest);
		} catch (RequestFailedException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testVerifySender() {
		MandrillRequestWithEmail emailRequest = new MandrillRequestWithEmail();
		emailRequest.setEmail("radar@cribbstechnologies.com");
		try {
			VerifyResponse response = usersRequest.verifySender(emailRequest);
		} catch (RequestFailedException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testDisableSender() {
		MandrillRequestWithDomain domainRequest = new MandrillRequestWithDomain();
		domainRequest.setDomain("google.com");
		try {
			DisableResponse response = usersRequest.disableSender(domainRequest);
		} catch (RequestFailedException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
