package com.cribbstechnologies.clients.mandrill.it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Properties;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import com.cribbstechnologies.clients.mandrill.model.BaseMandrillRequest;
import com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithCode;
import com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithName;
import com.cribbstechnologies.clients.mandrill.model.response.templates.TemplateResponse;
import com.cribbstechnologies.clients.mandrill.request.MandrillRESTRequest;
import com.cribbstechnologies.clients.mandrill.request.MandrillTemplatesRequest;
import com.cribbstechnologies.clients.mandrill.util.MandrillConfiguration;

public class TemplatesTest {

	private static MandrillRESTRequest request = new MandrillRESTRequest();
	private static MandrillConfiguration config = new MandrillConfiguration();
	private static MandrillTemplatesRequest templatesRequest = new MandrillTemplatesRequest();
	private static HttpClient client;
	private static ObjectMapper mapper = new ObjectMapper();
	
	@BeforeClass
	public static void beforeClass() {
		Properties props = new Properties();
		try {
			props.load(TemplatesTest.class.getClassLoader().getResourceAsStream("mandrill.properties"));
		} catch (IOException e) {
			fail ("properties file not loaded");
		}
		config.setApiKey(props.getProperty("apiKey"));
		config.setApiVersion("1.0");
		config.setBaseURL("https://mandrillapp.com/api");
		request.setConfig(config);
		request.setObjectMapper(mapper);
		templatesRequest.setRequest(request);
	}
	
	@Before 
	public void before() {
		client = new DefaultHttpClient();
		request.setHttpClient(client);
	}
	
	@Test
	public void testGetList() {
		BaseMandrillRequest listRequest = new BaseMandrillRequest();
		try {
			templatesRequest.getTemplates(listRequest);
		} catch (RequestFailedException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testAddTemplate() {
		MandrillRequestWithCode request = new MandrillRequestWithCode();
		request.setCode("<html><body>template</body></html>");
		request.setName("template1");
		try {
			templatesRequest.addTemplate(request);
		} catch (RequestFailedException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetTemplateInfo() {
		MandrillRequestWithName request = new MandrillRequestWithName();
		request.setName("template1");
		try {
			templatesRequest.getTemplateInfo(request);
		} catch (RequestFailedException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testUpdateTemplate() {
		MandrillRequestWithCode request = new MandrillRequestWithCode();
		request.setName("template1");
		String newVal = "<html><body>Not template</body></html>";
		request.setCode(newVal);
		MandrillRequestWithName retrieve = new MandrillRequestWithName();
		retrieve.setName("template1");
		
		try {
			TemplateResponse response = templatesRequest.getTemplateInfo(retrieve);
			String oldVal = response.getCode();
			templatesRequest.updateTemplate(request);
			response = templatesRequest.getTemplateInfo(retrieve);
			assertFalse(oldVal.equals(newVal));
			assertEquals(newVal, response.getCode());
		} catch (RequestFailedException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void deleteTemplate() {
		MandrillRequestWithName request = new MandrillRequestWithName();
		request.setName("template1");
		try {
			templatesRequest.deleteTemplate(request);
		} catch (RequestFailedException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
}
