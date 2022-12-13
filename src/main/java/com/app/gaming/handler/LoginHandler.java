package com.app.gaming.handler;


import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class LoginHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		
		String path = httpExchange.getRequestURI().toString();
		String[] pathArray = path.split("/");
		
		int statusCode = 200;
		String responseMessage = "Welcome " + pathArray[1];
		httpExchange.sendResponseHeaders(statusCode, responseMessage.length());
		OutputStream outputStream = httpExchange.getResponseBody();
		outputStream.write(responseMessage.getBytes());
		outputStream.close();		
	}

}
