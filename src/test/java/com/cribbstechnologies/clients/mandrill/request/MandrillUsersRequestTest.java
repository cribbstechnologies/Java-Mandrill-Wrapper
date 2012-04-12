package com.cribbstechnologies.clients.mandrill.request;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import com.cribbstechnologies.clients.mandrill.model.BaseMandrillRequest;
import com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithDomain;
import com.cribbstechnologies.clients.mandrill.model.MandrillRequestWithEmail;
import com.cribbstechnologies.clients.mandrill.model.ServiceMethods;
import com.cribbstechnologies.clients.mandrill.model.response.BaseMandrillStringResponse;
import com.cribbstechnologies.clients.mandrill.model.response.users.DisableResponse;
import com.cribbstechnologies.clients.mandrill.model.response.users.UsersInfoResponse;
import com.cribbstechnologies.clients.mandrill.model.response.users.UsersSendersResponse;
import com.cribbstechnologies.clients.mandrill.model.response.users.VerifyResponse;

public class MandrillUsersRequestTest {

	@Mock
	MandrillRESTRequest request;
	
	MandrillUsersRequest usersRequest = new MandrillUsersRequest();
	
	BaseMandrillRequest bmr;

	@Before
	public void before() {
		initMocks(this);
		
		bmr = new BaseMandrillRequest();
		bmr.setKey("123");
	}
	
	@Test
	public void testGetInfo() throws RequestFailedException {
		usersRequest.setRequest(request);
		
		UsersInfoResponse response = new UsersInfoResponse();
		
		Mockito.when(request.postRequest(bmr, ServiceMethods.Users.INFO, UsersInfoResponse.class)).thenReturn(response);
		
		usersRequest.getInfo(bmr);
		
		Mockito.verify(request).postRequest(bmr, ServiceMethods.Users.INFO, UsersInfoResponse.class);
	}
	
	@Test
	public void testPerformPing() throws RequestFailedException {
		usersRequest.setRequest(request);
		
		BaseMandrillStringResponse response = new BaseMandrillStringResponse();
		
		Mockito.when(request.postRequest(bmr, ServiceMethods.Users.PING, null)).thenReturn(response);
		usersRequest.performPing(bmr);
		Mockito.verify(request).postRequest(bmr, ServiceMethods.Users.PING, null);
	}
	
	@Test
	public void testGetSenders() throws RequestFailedException {
		usersRequest.setRequest(request);
		UsersSendersResponse response = new UsersSendersResponse();
		
		Mockito.when(request.postRequest(bmr, ServiceMethods.Users.SENDERS, UsersSendersResponse.class, usersRequest.getUsersListReference())).thenReturn(response);
		
		usersRequest.getSenders(bmr);
		
		Mockito.verify(request).postRequest(bmr, ServiceMethods.Users.SENDERS, UsersSendersResponse.class, usersRequest.getUsersListReference());
	}
	
	@Test
	public void testDisableSender() throws RequestFailedException {
		usersRequest.setRequest(request);
		MandrillRequestWithDomain disableRequest = new MandrillRequestWithDomain();
		DisableResponse response = new DisableResponse();
		
		Mockito.when(request.postRequest(disableRequest, ServiceMethods.Users.DISABLE_SENDER, DisableResponse.class)).thenReturn(response);
		
		usersRequest.disableSender(disableRequest);
		
		Mockito.verify(request).postRequest(disableRequest, ServiceMethods.Users.DISABLE_SENDER, DisableResponse.class);
	}
	
	@Test
	public void testVerifySender() throws RequestFailedException {
		usersRequest.setRequest(request);
		MandrillRequestWithEmail verifyRequest = new MandrillRequestWithEmail();
		VerifyResponse response = new VerifyResponse();
		
		Mockito.when(request.postRequest(verifyRequest, ServiceMethods.Users.VERIFY_SENDER, VerifyResponse.class)).thenReturn(response);
		
		usersRequest.verifySender(verifyRequest);
		
		Mockito.verify(request).postRequest(verifyRequest, ServiceMethods.Users.VERIFY_SENDER, VerifyResponse.class);
	}
	
}
