package com.app.gaming;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.app.gaming.utilities.AppConfigurationLoader;

public class TestUsecaseUpdateScore {

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
	public void testUpdateScore_returnOK_whenValidParameters() throws ClientProtocolException, IOException {
		
		HttpUriRequest request;
		HttpResponse response;
		String sessionKey;
		
		request = new HttpGet("http://localhost:" + portNumber + "/101/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/501/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity("1500"))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testUpdateScore_returnBadRequest_whenInvalidLevelId() throws ClientProtocolException, IOException {
		HttpUriRequest request;
		HttpResponse response;
		String sessionKey;
		
		request = new HttpGet("http://localhost:" + portNumber + "/101/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/0/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity("1500"))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
		
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/-125/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity("1500"))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
		
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/2147483648/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity("1500"))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
		
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/xyz/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity("1500"))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testUpdateScore_returnBadRequest_whenInvalidScore() throws ClientProtocolException, IOException {
		HttpUriRequest request;
		HttpResponse response;
		String sessionKey;
		
		request = new HttpGet("http://localhost:" + portNumber + "/101/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/501/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity("-123"))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
		
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/501/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity("2147483648"))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
		
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/501/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity("xyz"))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testUpdateScore_returnBadRequest_whenInvalidPath() throws ClientProtocolException, IOException {
		HttpUriRequest request;
		HttpResponse response;
		String sessionKey;
		
		request = new HttpGet("http://localhost:" + portNumber + "/101/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/501/score123?sessionkey=" + sessionKey)
				.setEntity(new StringEntity("1500"))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testUpdateScore_returnBadRequest_whenInvalidRequestMethod() throws ClientProtocolException, IOException {
		HttpUriRequest request;
		HttpResponse response;
		String sessionKey;
		
		request = new HttpGet("http://localhost:" + portNumber + "/101/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		
		request = new HttpGet("http://localhost:" + portNumber + "/101/score?sessionkey=" + sessionKey);
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
	}
}
