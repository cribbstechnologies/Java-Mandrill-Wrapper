package com.cribbstechnologies.clients.mandrill.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MandrillConfigurationTest {
	
	@Test
	public void testGetServiceUrl() {
		MandrillConfiguration config = new MandrillConfiguration();
		config.setApiVersion("1.0");
		config.setBaseURL("https://mandrillapp.com/api");
		
		String expectedResult = "https://mandrillapp.com/api/1.0/";
		
		assertEquals(expectedResult, config.getServiceUrl());
	}

}
