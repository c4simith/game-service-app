package com.app.gaming;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.app.gaming.utilities.AppConfigurationLoader;

public class TestUsecaseHighscore {

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
	public void testHighscore_returnOK_whenValidParameters() throws ClientProtocolException, IOException {
		HttpUriRequest request;
		HttpResponse response;
		String sessionKey;
		String responseMessage;
		String expectedHighscoreList;
		int userId;
		int levelId;
		int score;
		
		levelId = 501;
		
		userId = 101;
		score = 1000;
		request = new HttpGet("http://localhost:" + portNumber + "/" + userId + "/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/" + levelId + "/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity(score+""))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		
		userId = 102;
		score = 999;
		request = new HttpGet("http://localhost:" + portNumber + "/" + userId + "/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/" + levelId + "/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity(score+""))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		
		userId = 103;
		score = 1001;
		request = new HttpGet("http://localhost:" + portNumber + "/" + userId + "/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/" + levelId + "/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity(score+""))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		
		
		userId = 104;
		score = 1004;
		request = new HttpGet("http://localhost:" + portNumber + "/" + userId + "/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/" + levelId + "/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity(score+""))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		
		userId = 105;
		score = 995;
		request = new HttpGet("http://localhost:" + portNumber + "/" + userId + "/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/" + levelId + "/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity(score+""))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		
		userId = 106;
		score = 1006;
		request = new HttpGet("http://localhost:" + portNumber + "/" + userId + "/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/" + levelId + "/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity(score+""))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		
		userId = 107;
		score = 997;
		request = new HttpGet("http://localhost:" + portNumber + "/" + userId + "/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/" + levelId + "/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity(score+""))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		
		userId = 108;
		score = 1008;
		request = new HttpGet("http://localhost:" + portNumber + "/" + userId + "/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/" + levelId + "/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity(score+""))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		
		userId = 109;
		score = 1009;
		request = new HttpGet("http://localhost:" + portNumber + "/" + userId + "/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/" + levelId + "/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity(score+""))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		
		userId = 110;
		score = 990;
		request = new HttpGet("http://localhost:" + portNumber + "/" + userId + "/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/" + levelId + "/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity(score+""))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		
		userId = 111;
		score = 1011;
		request = new HttpGet("http://localhost:" + portNumber + "/" + userId + "/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/" + levelId + "/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity(score+""))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		
		userId = 112;
		score = 1011;
		request = new HttpGet("http://localhost:" + portNumber + "/" + userId + "/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/" + levelId + "/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity(score+""))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		
		userId = 113;
		score = 1113;
		request = new HttpGet("http://localhost:" + portNumber + "/" + userId + "/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/" + levelId + "/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity(score+""))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		
		
		userId = 114;
		score = 1004;
		request = new HttpGet("http://localhost:" + portNumber + "/" + userId + "/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/" + levelId + "/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity(score+""))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		
		userId = 115;
		score = 995;
		request = new HttpGet("http://localhost:" + portNumber + "/" + userId + "/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/" + levelId + "/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity(score+""))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		
		
		userId = 116;
		score = 35;
		request = new HttpGet("http://localhost:" + portNumber + "/" + userId + "/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/" + levelId + "/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity(score+""))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		
		userId = 117;
		score = 34;
		request = new HttpGet("http://localhost:" + portNumber + "/" + userId + "/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/" + levelId + "/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity(score+""))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		
		request = new HttpGet("http://localhost:" + portNumber + "/" + levelId + "/highscorelist");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		expectedHighscoreList = "113=1113,112=1011,111=1011,109=1009,108=1008,106=1006,114=1004,104=1004,103=1001,101=1000,102=999,107=997,115=995,105=995,110=990";
		responseMessage = EntityUtils.toString(response.getEntity());
		assertEquals(expectedHighscoreList, responseMessage);
		
		
		levelId=999;
		
		userId = 998;
		score = 999;
		request = new HttpGet("http://localhost:" + portNumber + "/" + userId + "/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/" + levelId + "/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity(score+""))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		
		userId = 999;
		score = 1000;
		request = new HttpGet("http://localhost:" + portNumber + "/" + userId + "/login");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		sessionKey = EntityUtils.toString(response.getEntity());
		request = RequestBuilder.create("POST")
				.setUri("http://localhost:" + portNumber + "/" + levelId + "/score?sessionkey=" + sessionKey)
				.setEntity(new StringEntity(score+""))
			    .build();
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		
		request = new HttpGet("http://localhost:" + portNumber + "/" + levelId + "/highscorelist");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		expectedHighscoreList = "999=1000,998=999";
		responseMessage = EntityUtils.toString(response.getEntity());
		assertEquals(expectedHighscoreList, responseMessage);
	}
	
	@Test
	public void testHighscore_returnBadRequest_whenInvalidLevelId() throws ClientProtocolException, IOException {
		HttpUriRequest request;
		HttpResponse response;
		String levelId;
		
		levelId="0";
		request = new HttpGet("http://localhost:" + portNumber + "/" + levelId + "/highscorelist");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
		
		levelId="-101";
		request = new HttpGet("http://localhost:" + portNumber + "/" + levelId + "/highscorelist");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
		
		levelId="2147483648";
		request = new HttpGet("http://localhost:" + portNumber + "/" + levelId + "/highscorelist");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
		
		levelId="xyz";
		request = new HttpGet("http://localhost:" + portNumber + "/" + levelId + "/highscorelist");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testHighscore_returnBadRequest_whenInvalidPath() throws ClientProtocolException, IOException {
		HttpUriRequest request;
		HttpResponse response;
	
		request = new HttpGet("http://localhost:" + portNumber + "/101/highscorelist123");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
	}
	
	@Test
	public void testHighscore_returnBadRequest_whenInvalidRequestMethod() throws ClientProtocolException, IOException {
		HttpUriRequest request;
		HttpResponse response;
	
		request = new HttpPost("http://localhost:" + portNumber + "/101/highscorelist");
		response = HttpClientBuilder.create().build().execute(request);
		assertEquals(HttpStatus.SC_BAD_REQUEST, response.getStatusLine().getStatusCode());
	}
	
}
