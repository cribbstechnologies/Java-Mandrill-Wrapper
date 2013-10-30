package com.cribbstechnologies.clients.mandrill.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
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
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.cribbstechnologies.clients.mandrill.exception.RequestFailedException;
import com.cribbstechnologies.clients.mandrill.model.BaseMandrillRequest;
import com.cribbstechnologies.clients.mandrill.model.MandrillHtmlMessage;
import com.cribbstechnologies.clients.mandrill.model.MandrillMessageAttachment;
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
		config.setApiVersion("1.0");
		config.setBaseURL("https://mandrillapp.com/api");
	}
	
	@Test
	public void testGetPostDataJsonGenerationException() throws Exception {
		initRequestWithMockedMapper();
				
		Mockito.when(mapper.writeValueAsString(emptyBaseRequest)).thenThrow(new JsonGenerationException("Mockito!"));
		try {
			request.getPostData(emptyBaseRequest);
			fail("Exception not thrown");
		} catch (JsonGenerationException jge) {
			assertEquals("Mockito!", jge.getMessage());
		}
	}
	
	@Test
	public void testGetPostDataJsonMappingException() throws Exception {
		initRequestWithMockedMapper();
				
		Mockito.when(mapper.writeValueAsString(emptyBaseRequest)).thenThrow(new JsonMappingException("Mockito!"));
		try {
			request.getPostData(emptyBaseRequest);
		} catch (JsonMappingException jme) {
			assertEquals("Mockito!", jme.getMessage());
		}
	}
	
	@Test
	public void testGetPostDataIOException() throws Exception {
		initRequestWithMockedMapper();
				
		Mockito.when(mapper.writeValueAsString(emptyBaseRequest)).thenThrow(new IOException("Mockito!"));
		try {
			request.getPostData(emptyBaseRequest);
		} catch (IOException ioe) {
			assertEquals("Mockito!", ioe.getMessage());
		}
	}
	
	private void initRequestWithMockedMapper() {
		request = new MandrillRESTRequest();
		request.setObjectMapper(mapper);
	}
	
	private void initRequestWithActualMapper() {
		request = new MandrillRESTRequest();
		request.setObjectMapper(new ObjectMapper());
	}
	
	@Test
	public void testGetPostDataBaseMandrillRequest() throws Exception {
		initRequestWithActualMapper();
		
		assertEquals("{\"key\":null}", request.getPostData(emptyBaseRequest));
		mutableBaseRequest = new BaseMandrillRequest();
		mutableBaseRequest.setKey("this is my key");
		assertEquals("{\"key\":\"this is my key\"}", request.getPostData(mutableBaseRequest));
		
		mutableBaseRequest.setKey("this is my key with \"extra\" quotes");
		assertEquals("{\"key\":\"this is my key with \\\"extra\\\" quotes\"}", request.getPostData(mutableBaseRequest));
		
	}
	
	@Test
	public void testGetPostDataMandrillRequestWithEmail() throws Exception{
		initRequestWithActualMapper();
		
		assertEquals("{\"key\":null,\"domain\":null}", request.getPostData(emptyEmailRequest));
		mutableEmailRequest = new MandrillRequestWithDomain();
		mutableEmailRequest.setKey("12345");
		mutableEmailRequest.setDomain("email@email.com");
		assertEquals("{\"key\":\"12345\",\"domain\":\"email@email.com\"}", request.getPostData(mutableEmailRequest));
	}
	
	@Test
	public void testGetPostDataMandrillRequestWithQuery() throws Exception {
		initRequestWithActualMapper();
		
		assertEquals("{\"key\":null,\"q\":null}", request.getPostData(emptyQueryRequest));
		mutableQueryRequest = new MandrillRequestWithQuery();
		mutableQueryRequest.setKey("7890");
		mutableQueryRequest.setQ("query string");
		assertEquals("{\"key\":\"7890\",\"q\":\"query string\"}", request.getPostData(mutableQueryRequest));
	}
	
	@Test
	public void testGetPostDataMandrillRequestWithTag() throws Exception {
		initRequestWithActualMapper();
		
		assertEquals("{\"key\":null,\"tag\":null}", request.getPostData(emptyTagRequest));
		mutableTagRequest = new MandrillRequestWithTag();
		mutableTagRequest.setKey("ABC");
		mutableTagRequest.setTag("Tag, you're it");
		assertEquals("{\"key\":\"ABC\",\"tag\":\"Tag, you're it\"}", request.getPostData(mutableTagRequest));
	}
	
	@Test
	public void testGetPostDataMandrillRequestWithUrl() throws Exception {
		initRequestWithActualMapper();
		
		assertEquals("{\"key\":null,\"url\":null}", request.getPostData(emptyUrlRequest));
		mutableUrlRequest = new MandrillRequestWithUrl();
		mutableUrlRequest.setKey("TEST");
		mutableUrlRequest.setUrl("http://www.google.com");
		assertEquals("{\"key\":\"TEST\",\"url\":\"http://www.google.com\"}", request.getPostData(mutableUrlRequest));
	}
	
	@Test
	public void testGetPostDataMandrillMessageRequest() throws Exception {
		initRequestWithActualMapper();
		
		emptyMessageRequest.setMessage(emptyMessage);
		assertEquals("{\"key\":null,\"message\":null}", request.getPostData(emptyMessageRequest));
		
		mutableMessageRequest = new MandrillMessageRequest();
		mutableMessageRequest.setKey("API Key");
		mutableMessage = new MandrillHtmlMessage();
		mutableMessage.setHtml("Test html");
		mutableMessage.setText("Test text");
		mutableMessage.setSubject("Test subject");
		mutableMessage.setFrom_email("from@email.com");
		mutableMessage.setFrom_name("From Name");
		MandrillRecipient[] to = new MandrillRecipient[2];
		to[0] = new MandrillRecipient("to1", "to1");
		to[1] = new MandrillRecipient("to2", "to2");
		mutableMessage.setTo(to);
		mutableMessage.setTrack_opens(false);
		mutableMessage.setTrack_clicks(true);
		String[] tags = new String[2];
		tags[0] = "tag1";
		tags[1] = "tag2";
		mutableMessage.setTags(tags);
        List<MandrillMessageAttachment> attachments = new ArrayList<MandrillMessageAttachment>();
        MandrillMessageAttachment a = new MandrillMessageAttachment(new File(getClass().getClassLoader().getResource("attachments/test.pdf").getFile()), "application/pdf");
        attachments.add(a);
        mutableMessage.setAttachments(attachments);
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("headerName", "headerValue");
		
		mutableMessage.setHeaders(headerMap);
		
		mutableMessageRequest.setMessage(mutableMessage);
//		System.out.println(request.getPostData(mutableMessageRequest));
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"key\":\"API Key\"");
		sb.append(",\"message\":{");
		sb.append("\"text\":\"Test text\"");
		sb.append(",\"subject\":\"Test subject\"");
		sb.append(",\"from_email\":\"from@email.com\"");
		sb.append(",\"from_name\":\"From Name\"");
		sb.append(",\"to\":[{\"email\":\"to1\",\"name\":\"to1\"},{\"email\":\"to2\",\"name\":\"to2\"}]");
		sb.append(",\"track_opens\":false");
		sb.append(",\"track_clicks\":true");
		sb.append(",\"auto_text\":false");
		sb.append(",\"url_strip_qs\":false");
		sb.append(",\"tags\":[\"tag1\",\"tag2\"]");
        sb.append(",\"attachments\":[{\"type\":\"application/pdf\",\"name\":\"test.pdf\",\"content\":\"JVBERi0xLjINJeLjz9MNCjMgMCBvYmoNPDwgDS9MaW5lYXJpemVkIDEgDS9PIDUgDS9IIFsgNzYwIDE1NyBdIA0vTCAzOTA4IA0vRSAzNjU4IA0vTiAxIA0vVCAzNzMxIA0+PiANZW5kb2JqDSAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICB4cmVmDTMgMTUgDTAwMDAwMDAwMTYgMDAwMDAgbg0KMDAwMDAwMDY0NCAwMDAwMCBuDQowMDAwMDAwOTE3IDAwMDAwIG4NCjAwMDAwMDEwNjggMDAwMDAgbg0KMDAwMDAwMTIyNCAwMDAwMCBuDQowMDAwMDAxNDEwIDAwMDAwIG4NCjAwMDAwMDE1ODkgMDAwMDAgbg0KMDAwMDAwMTc2OCAwMDAwMCBuDQowMDAwMDAyMTk3IDAwMDAwIG4NCjAwMDAwMDIzODMgMDAwMDAgbg0KMDAwMDAwMjc2OSAwMDAwMCBuDQowMDAwMDAzMTcyIDAwMDAwIG4NCjAwMDAwMDMzNTEgMDAwMDAgbg0KMDAwMDAwMDc2MCAwMDAwMCBuDQowMDAwMDAwODk3IDAwMDAwIG4NCnRyYWlsZXINPDwNL1NpemUgMTgNL0luZm8gMSAwIFIgDS9Sb290IDQgMCBSIA0vUHJldiAzNzIyIA0vSURbPGQ3MGY0NmM1YmE0ZmU4YmQ0OWE5ZGQwNTk5YjBiMTUxPjxkNzBmNDZjNWJhNGZlOGJkNDlhOWRkMDU5OWIwYjE1MT5dDT4+DXN0YXJ0eHJlZg0wDSUlRU9GDSAgICAgIA00IDAgb2JqDTw8IA0vVHlwZSAvQ2F0YWxvZyANL1BhZ2VzIDIgMCBSIA0vT3BlbkFjdGlvbiBbIDUgMCBSIC9YWVogbnVsbCBudWxsIG51bGwgXSANL1BhZ2VNb2RlIC9Vc2VOb25lIA0+PiANZW5kb2JqDTE2IDAgb2JqDTw8IC9TIDM2IC9GaWx0ZXIgL0ZsYXRlRGVjb2RlIC9MZW5ndGggMTcgMCBSID4+IA1zdHJlYW0NCkiJYmBg4GVgYPrBAAScFxiwAQ4oLQDE3FDMwODHwKkyubctWLfmpsmimQ5AEYAAAwC3vwe0DWVuZHN0cmVhbQ1lbmRvYmoNMTcgMCBvYmoNNTMgDWVuZG9iag01IDAgb2JqDTw8IA0vVHlwZSAvUGFnZSANL1BhcmVudCAyIDAgUiANL1Jlc291cmNlcyA2IDAgUiANL0NvbnRlbnRzIDEwIDAgUiANL01lZGlhQm94IFsgMCAwIDYxMiA3OTIgXSANL0Nyb3BCb3ggWyAwIDAgNjEyIDc5MiBdIA0vUm90YXRlIDAgDT4+IA1lbmRvYmoNNiAwIG9iag08PCANL1Byb2NTZXQgWyAvUERGIC9UZXh0IF0gDS9Gb250IDw8IC9UVDIgOCAwIFIgL1RUNCAxMiAwIFIgL1RUNiAxMyAwIFIgPj4gDS9FeHRHU3RhdGUgPDwgL0dTMSAxNSAwIFIgPj4gDS9Db2xvclNwYWNlIDw8IC9DczUgOSAwIFIgPj4gDT4+IA1lbmRvYmoNNyAwIG9iag08PCANL1R5cGUgL0ZvbnREZXNjcmlwdG9yIA0vQXNjZW50IDg5MSANL0NhcEhlaWdodCAwIA0vRGVzY2VudCAtMjE2IA0vRmxhZ3MgMzQgDS9Gb250QkJveCBbIC01NjggLTMwNyAyMDI4IDEwMDcgXSANL0ZvbnROYW1lIC9UaW1lc05ld1JvbWFuIA0vSXRhbGljQW5nbGUgMCANL1N0ZW1WIDAgDT4+IA1lbmRvYmoNOCAwIG9iag08PCANL1R5cGUgL0ZvbnQgDS9TdWJ0eXBlIC9UcnVlVHlwZSANL0ZpcnN0Q2hhciAzMiANL0xhc3RDaGFyIDMyIA0vV2lkdGhzIFsgMjUwIF0gDS9FbmNvZGluZyAvV2luQW5zaUVuY29kaW5nIA0vQmFzZUZvbnQgL1RpbWVzTmV3Um9tYW4gDS9Gb250RGVzY3JpcHRvciA3IDAgUiANPj4gDWVuZG9iag05IDAgb2JqDVsgDS9DYWxSR0IgPDwgL1doaXRlUG9pbnQgWyAwLjk1MDUgMSAxLjA4OSBdIC9HYW1tYSBbIDIuMjIyMjEgMi4yMjIyMSAyLjIyMjIxIF0gDS9NYXRyaXggWyAwLjQxMjQgMC4yMTI2IDAuMDE5MyAwLjM1NzYgMC43MTUxOSAwLjExOTIgMC4xODA1IDAuMDcyMiAwLjk1MDUgXSA+PiANDV0NZW5kb2JqDTEwIDAgb2JqDTw8IC9MZW5ndGggMzU1IC9GaWx0ZXIgL0ZsYXRlRGVjb2RlID4+IA1zdHJlYW0NCkiJdJDBTsMwEETv/oo92ohuvXHsJEeggOCEwDfEIU1SCqIJIimIv2dthyJVQpGc0Xo88+xzL5beZ0DgN4IIq6oCzd8sK43amAyK3GKmTQV+J5YXo4VmjDYNYyOW1w8Ez6PQ4JuwfAkJyr+yXNgSSwt+NU+4Kp+rcg4uy9Q1a6MdarLcpgvUeUGh7RBFSLk1f1n+5FgsHJaZttFqA+tKLJhfZ3kEY+VcoHuUfvui2O3kCL9COSwk1Ok3deMEd6srUCVa2Q7Nftf1Ewar5a4nfxuu4v59NcLMGAKXlcjMLtwj1BsTQCITUSK52cC3IoNGDnto6l5VmEv4YAwjO8VWJ+s2DSeGttw/qmA/PZyLu3vY1p9p0MGZIs2iHdZxjwdNSkzedT0pJiW+CWl5H0O7uu2SB1JLn8rHlMkH2F+/xa20Rjp+nAQ39Ec8c1gz7KJ4T3H7uXnuwvSWl178CDAA/bGPlAplbmRzdHJlYW0NZW5kb2JqDTExIDAgb2JqDTw8IA0vVHlwZSAvRm9udERlc2NyaXB0b3IgDS9Bc2NlbnQgOTA1IA0vQ2FwSGVpZ2h0IDAgDS9EZXNjZW50IC0yMTEgDS9GbGFncyAzMiANL0ZvbnRCQm94IFsgLTYyOCAtMzc2IDIwMzQgMTA0OCBdIA0vRm9udE5hbWUgL0FyaWFsLEJvbGQgDS9JdGFsaWNBbmdsZSAwIA0vU3RlbVYgMTMzIA0+PiANZW5kb2JqDTEyIDAgb2JqDTw8IA0vVHlwZSAvRm9udCANL1N1YnR5cGUgL1RydWVUeXBlIA0vRmlyc3RDaGFyIDMyIA0vTGFzdENoYXIgMTE3IA0vV2lkdGhzIFsgMjc4IDAgMCAwIDAgMCAwIDAgMCAwIDAgMCAwIDAgMjc4IDAgMCAwIDAgMCAwIDAgMCAwIDAgMCAwIDAgMCAwIDAgDTAgMCAwIDAgMCA3MjIgMCA2MTEgMCAwIDAgMCAwIDAgMCAwIDAgNjY3IDAgMCAwIDYxMSAwIDAgMCAwIDAgMCANMCAwIDAgMCAwIDAgNTU2IDAgNTU2IDYxMSA1NTYgMCAwIDYxMSAyNzggMCAwIDAgODg5IDYxMSA2MTEgMCAwIA0wIDU1NiAzMzMgNjExIF0gDS9FbmNvZGluZyAvV2luQW5zaUVuY29kaW5nIA0vQmFzZUZvbnQgL0FyaWFsLEJvbGQgDS9Gb250RGVzY3JpcHRvciAxMSAwIFIgDT4+IA1lbmRvYmoNMTMgMCBvYmoNPDwgDS9UeXBlIC9Gb250IA0vU3VidHlwZSAvVHJ1ZVR5cGUgDS9GaXJzdENoYXIgMzIgDS9MYXN0Q2hhciAxMjEgDS9XaWR0aHMgWyAyNzggMCAwIDAgMCAwIDAgMCAwIDAgMCAwIDI3OCAwIDI3OCAwIDAgMCAwIDAgMCAwIDAgMCAwIDAgMCAwIDAgMCANMCAwIDAgNjY3IDAgMCAwIDAgMCAwIDAgMjc4IDAgMCAwIDAgMCAwIDAgMCA3MjIgMCAwIDAgMCAwIDAgMCAwIA0wIDAgMCAwIDAgMCA1NTYgNTU2IDUwMCA1NTYgNTU2IDI3OCAwIDU1NiAyMjIgMCAwIDIyMiA4MzMgNTU2IDU1NiANNTU2IDAgMzMzIDUwMCAyNzggNTU2IDUwMCAwIDAgNTAwIF0gDS9FbmNvZGluZyAvV2luQW5zaUVuY29kaW5nIA0vQmFzZUZvbnQgL0FyaWFsIA0vRm9udERlc2NyaXB0b3IgMTQgMCBSIA0+PiANZW5kb2JqDTE0IDAgb2JqDTw8IA0vVHlwZSAvRm9udERlc2NyaXB0b3IgDS9Bc2NlbnQgOTA1IA0vQ2FwSGVpZ2h0IDAgDS9EZXNjZW50IC0yMTEgDS9GbGFncyAzMiANL0ZvbnRCQm94IFsgLTY2NSAtMzI1IDIwMjggMTAzNyBdIA0vRm9udE5hbWUgL0FyaWFsIA0vSXRhbGljQW5nbGUgMCANL1N0ZW1WIDAgDT4+IA1lbmRvYmoNMTUgMCBvYmoNPDwgDS9UeXBlIC9FeHRHU3RhdGUgDS9TQSBmYWxzZSANL1NNIDAuMDIgDS9UUiAvSWRlbnRpdHkgDT4+IA1lbmRvYmoNMSAwIG9iag08PCANL1Byb2R1Y2VyIChBY3JvYmF0IERpc3RpbGxlciA0LjA1IGZvciBXaW5kb3dzKQ0vQ3JlYXRvciAoTWljcm9zb2Z0IFdvcmQgOS4wKQ0vTW9kRGF0ZSAoRDoyMDAxMDgyOTA5NTUwMS0wNycwMCcpDS9BdXRob3IgKEdlbmUgQnJ1bWJsYXkpDS9UaXRsZSAoVGhpcyBpcyBhIHRlc3QgUERGIGRvY3VtZW50KQ0vQ3JlYXRpb25EYXRlIChEOjIwMDEwODI5MDk1NDU3KQ0+PiANZW5kb2JqDTIgMCBvYmoNPDwgDS9UeXBlIC9QYWdlcyANL0tpZHMgWyA1IDAgUiBdIA0vQ291bnQgMSANPj4gDWVuZG9iag14cmVmDTAgMyANMDAwMDAwMDAwMCA2NTUzNSBmDQowMDAwMDAzNDI5IDAwMDAwIG4NCjAwMDAwMDM2NTggMDAwMDAgbg0KdHJhaWxlcg08PA0vU2l6ZSAzDS9JRFs8ZDcwZjQ2YzViYTRmZThiZDQ5YTlkZDA1OTliMGIxNTE+PGQ3MGY0NmM1YmE0ZmU4YmQ0OWE5ZGQwNTk5YjBiMTUxPl0NPj4Nc3RhcnR4cmVmDTE3Mw0lJUVPRg0=\"}]");
		sb.append(",\"google_analytics_domains\":[]");
		sb.append(",\"google_analytics_campaign\":[]");
        sb.append(",\"global_merge_vars\":null");
        sb.append(",\"merge_vars\":null");
		sb.append(",\"headers\":{\"headerName\":\"headerValue\"},");
		sb.append("\"html\":\"Test html\"");
		sb.append("}}");
		String output = request.getPostData(mutableMessageRequest);
		System.out.println("Comparing:\n" + sb.toString() + "\n" + output);
		assertEquals(sb.toString(), output);
	}
	
	@Test
	public void testPostRequest() throws ClientProtocolException, IOException {
		request = new MandrillRESTRequest();
		request.setHttpClient(client);
		request.setConfig(config);
		request.setObjectMapper(new ObjectMapper());

		doThrow(new MalformedURLException("Mockito!")).when(client).execute(isA(HttpPost.class));
		try {
			request.postRequest(emptyBaseRequest, "test", null);
			fail("Exception not thrown");
		} catch (RequestFailedException e) {
			assertEquals("Malformed url", e.getMessage());
		}
		
		doThrow(new IOException("Mockito!")).when(client).execute(isA(HttpPost.class));
		try {
			request.postRequest(emptyBaseRequest, "test", null);
			fail("Exception not thrown");
		} catch (RequestFailedException e) {
			assertEquals("IOException", e.getMessage());
		}
	}
	
	@Test
	public void testPostRequestMapperExceptions() throws ClientProtocolException, IOException {
		request = new MandrillRESTRequest();
		request.setHttpClient(client);
		request.setConfig(config);
		request.setObjectMapper(mapper);

		doThrow(new JsonGenerationException("Mockito!")).when(mapper).writeValueAsString(isA(BaseMandrillRequest.class));
		try {
			request.postRequest(emptyBaseRequest, "test", null);
			fail("Exception not thrown");
		} catch (RequestFailedException e) {
			assertEquals("Json Generation Exception", e.getMessage());
		}
		
		doThrow(new JsonMappingException("Mockito!")).when(mapper).writeValueAsString(isA(BaseMandrillRequest.class));
		try {
			request.postRequest(emptyBaseRequest, "test", null);
			fail("Exception not thrown");
		} catch (RequestFailedException e) {
			assertEquals("Json Mapping Exception", e.getMessage());
		}
	}
	
	@Test
	public void testPostRequestNon200Response() {
		try {
			request = new MandrillRESTRequest();
			request.setHttpClient(client);
			request.setConfig(config);
			request.setObjectMapper(mapper);

			doReturn("postData").when(mapper).writeValueAsString(emptyBaseRequest);
			doReturn(response).when(client).execute(isA(HttpPost.class));
			doReturn(manager).when(client).getConnectionManager();
			Mockito.when(response.getEntity()).thenReturn(this.entity);
			InputStream inputStream = IOUtils.toInputStream("INPUT");
			Mockito.when(entity.getContent()).thenReturn(inputStream);
			Mockito.when(response.getStatusLine()).thenReturn(statusLine);
			Mockito.when(statusLine.getStatusCode()).thenReturn(500);
			
			request.postRequest(emptyBaseRequest, "Foo", null);
		} catch (RequestFailedException rfe) {
			assertEquals("Failed : HTTP error code : 500 INPUT", rfe.getMessage());
		} catch (ClientProtocolException e) {
			fail("Mockito is a good mocking framework, this shouldn't happen");
		} catch (IOException e) {
			fail("Mockito is a good mocking framework, this shouldn't happen");
		}
	}
	
	@Test
	public void testUsersInfoResponseConversion() throws JsonParseException, JsonMappingException, IOException {
		initRequestWithActualMapper();
		
		StringWriter sw = new StringWriter();
		IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("users/infoResponse.txt"), sw);
		UsersInfoResponse myResponse = (UsersInfoResponse) request.convertResponseData(sw.toString(), UsersInfoResponse.class);

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
	public void testSendersResponseConversion() throws IOException {
		initRequestWithActualMapper();
		
		StringWriter sw = new StringWriter();
		IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("users/sendersResponse.txt"), sw);
		TypeReference usersListReference = new TypeReference<List<MandrillSender>>(){};
		BaseMandrillAnonymousListResponse<MandrillSender> response = (BaseMandrillAnonymousListResponse<MandrillSender>) request.convertAnonymousListResponseData(sw.toString(), UsersSendersResponse.class, usersListReference);
		
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
	public void testDisableResponseConversion() throws IOException {
		initRequestWithActualMapper();
		
		StringWriter sw = new StringWriter();
		IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("users/disableSenderResponse.txt"), sw);
		DisableResponse response = (DisableResponse) request.convertResponseData(sw.toString(), DisableResponse.class);
		
		assertEquals("example domain", response.getDomain());
		assertEquals("example created_at", response.getCreated_at());
		assertEquals("example approved_at", response.getApproved_at());
		assertTrue(response.isIs_enabled());
	}
	
	@Test
	public void testVerifySenderResponseConversion() throws IOException {
		initRequestWithActualMapper();
		
		StringWriter sw = new StringWriter();
		IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("users/verifySenderResponse.txt"), sw);
		DisableResponse response = (DisableResponse) request.convertResponseData(sw.toString(), DisableResponse.class);
		
		assertEquals("example domain", response.getDomain());
		assertEquals("example created_at", response.getCreated_at());
		assertEquals("example approved_at", response.getApproved_at());
		assertTrue(response.isIs_enabled());
	}
	
	@Test
	public void testSendMessageResponseConversion() throws IOException {
		initRequestWithActualMapper();
		
		StringWriter sw = new StringWriter();
		IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("messages/sendMessageResponse.txt"), sw);
		TypeReference responseListReference = new TypeReference<List<MessageResponse>>(){};
		BaseMandrillAnonymousListResponse<MessageResponse> response = (BaseMandrillAnonymousListResponse<MessageResponse>) request.convertAnonymousListResponseData(sw.toString(), SendMessageResponse.class, responseListReference);
		
		assertEquals(2, response.getList().size());
		
		MessageResponse resp = response.getList().get(0);
		assertEquals("example email", resp.getEmail());
		assertEquals("example status", resp.getStatus());
		
		resp = response.getList().get(1);
		assertEquals("example email2", resp.getEmail());
		assertEquals("example status2", resp.getStatus());
	}
	
	@Test
	public void testListTagsResponseConversion() throws IOException {
		initRequestWithActualMapper();
		
		StringWriter sw = new StringWriter();
		IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("tags/listResponse.txt"), sw);
		TypeReference tagListReference = new TypeReference<List<BaseTag>>(){};
		BaseMandrillAnonymousListResponse<BaseTag> response = (BaseMandrillAnonymousListResponse<BaseTag>) request.convertAnonymousListResponseData(sw.toString(), TagListResponse.class, tagListReference);
		
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
	public void testTimeTagsResponseConversion() throws IOException {
		initRequestWithActualMapper();
		
		StringWriter sw = new StringWriter();
		IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("tags/timeSeriesResponse.txt"), sw);
		TypeReference tagListReference = new TypeReference<List<TagWithTime>>(){};
		BaseMandrillAnonymousListResponse<TagWithTime> response = (BaseMandrillAnonymousListResponse<TagWithTime>) request.convertAnonymousListResponseData(sw.toString(), TagSeriesResponse.class, tagListReference);
		
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
		initRequestWithActualMapper();
		
		StringWriter sw = new StringWriter();
		IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("urls/urlList.txt"), sw);
		TypeReference urlListReference = new TypeReference<List<UrlResponse>>(){};
		BaseMandrillAnonymousListResponse<UrlResponse> response = (BaseMandrillAnonymousListResponse<UrlResponse>) request.convertAnonymousListResponseData(sw.toString(), UrlListResponse.class, urlListReference);
		
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
		initRequestWithActualMapper();
		
		StringWriter sw = new StringWriter();
		IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("urls/urlTimeResponse.txt"), sw);
		TypeReference urlTimeReference = new TypeReference<List<TimeUrlResponse>>(){};
		BaseMandrillAnonymousListResponse<TimeUrlResponse> response = (BaseMandrillAnonymousListResponse<TimeUrlResponse>) request.convertAnonymousListResponseData(sw.toString(), UrlTimeResponse.class, urlTimeReference);
		
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
	public void testTemplateResponseConversion() throws IOException {
		initRequestWithActualMapper();
		
		StringWriter sw = new StringWriter();
		IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("templates/templateResponse.txt"), sw);
		
		TemplateResponse response = (TemplateResponse) request.convertResponseData(sw.toString(), TemplateResponse.class);
		
		assertEquals("example name", response.getName());
		assertEquals("example code", response.getCode());
		assertEquals("example created_at", response.getCreated_at());
		assertEquals("example updated_at", response.getUpdated_at());
	}
	@Test
	public void testTemplateListResponseConversion() throws IOException {
		initRequestWithActualMapper();
		
		StringWriter sw = new StringWriter();
		IOUtils.copy(this.getClass().getClassLoader().getResourceAsStream("templates/templatesListResponse.txt"), sw);
		TypeReference templatesListReference = new TypeReference<List<TemplateResponse>>(){};
		BaseMandrillAnonymousListResponse<TemplateResponse> response = (BaseMandrillAnonymousListResponse<TemplateResponse>) request.convertAnonymousListResponseData(sw.toString(), TemplateResponse.class, templatesListReference);
		
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
}