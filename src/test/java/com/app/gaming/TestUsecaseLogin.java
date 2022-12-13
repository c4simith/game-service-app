package com.app.gaming;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.app.gaming.utilities.AppConfigurationLoader;

public class TestUsecaseLogin {

	private static String portNumber;
	@BeforeClass
    public static void init(){
		GameServiceApp.main(null);
		String configFilePath = "." + File.separator +  "AppConfig.properties";
		AppConfigurationLoader appConfigurationLoader = new AppConfigurationLoader();
		Properties properties = appConfigurationLoader.loadProperties(configFilePath);
		portNumber = properties.getProperty("server.port");
    }
	
	@Test
	public void testLogin_returnOK_whenValidParameters() throws ClientProtocolException, IOException {
		HttpUriRequest request;
		HttpResponse response;
		String responseMessage;
		
		request = new HttpGet("http://localhost:" + portNumber + "/1/login");
		response = HttpClientBuilder.create().build().execute(request);
		responseMessage = EntityUtils.toString(response.getEntity());
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		assertTrue(responseMessage.matches("^[a-zA-Z0-9]*$"));
		
		request = new HttpGet("http://localhost:" + portNumber + "/101/login");
		response = HttpClientBuilder.create().build().execute(request);
		responseMessage = EntityUtils.toString(response.getEntity());
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		assertTrue(responseMessage.matches("^[a-zA-Z0-9]*$"));
		
		request = new HttpGet("http://localhost:" + portNumber + "/2059/login");
		response = HttpClientBuilder.create().build().execute(request);
		responseMessage = EntityUtils.toString(response.getEntity());
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		assertTrue(responseMessage.matches("^[a-zA-Z0-9]*$"));
		
		request = new HttpGet("http://localhost:" + portNumber + "/2147483647/login");
		response = HttpClientBuilder.create().build().execute(request);
		responseMessage = EntityUtils.toString(response.getEntity());
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		assertTrue(responseMessage.matches("^[a-zA-Z0-9]*$"));
	}
	
	@Test
	public void testLogin_returnBadRequest_whenInvalidUserId_ZeroOrNegative() throws ClientProtocolException, IOException {
		HttpUriRequest request;
		HttpResponse response;

		request = new HttpGet("http://localhost:" + portNumber + "/0/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
		
		request = new HttpGet("http://localhost:" + portNumber + "/-101/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testLogin_returnBadRequest_whenInvalidUserId_OutofIntegerRange() throws ClientProtocolException, IOException {
		HttpUriRequest request;
		HttpResponse response;
	
		request = new HttpGet("http://localhost:" + portNumber + "/2147483648/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testLogin_returnBadRequest_whenInvalidUserId_NonInteger() throws ClientProtocolException, IOException {
		HttpUriRequest request;
		HttpResponse response;

		request = new HttpGet("http://localhost:" + portNumber + "/xyz/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
		
		request = new HttpGet("http://localhost:" + portNumber + "/xyz123/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testLogin_returnBadRequest_whenInvalidPath() throws ClientProtocolException, IOException {
		HttpUriRequest request;
		HttpResponse response;
	
		request = new HttpGet("http://localhost:" + portNumber + "/101/login1");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
		
		request = new HttpGet("http://localhost:" + portNumber + "/101/12login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testLogin_returnBadRequest_whenInvalidRequestMethod() throws ClientProtocolException, IOException {
		HttpUriRequest request;
		HttpResponse response;
	
		request = new HttpPost("http://localhost:" + portNumber + "/101/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
	}
}
