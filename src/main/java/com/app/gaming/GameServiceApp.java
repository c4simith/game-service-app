package com.app.gaming;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.app.gaming.constants.AppConstants;
import com.app.gaming.handler.LoginHandler;
import com.app.gaming.handler.RootHandler;
import com.app.gaming.utilities.AppConfigurationLoader;
import com.app.gaming.utilities.AppLogger;
import com.app.gaming.utilities.SessionInvalidator;
import com.sun.net.httpserver.HttpServer;

/**
 * 
 * Application main class.
 * Initializes the server and session cleanup threads.
 *
 */
public class GameServiceApp {

	public static void main(String[] args) {

		ConcurrentMap<Integer, String> sessionMap = new ConcurrentHashMap<>();
		ConcurrentMap<Integer, Map<Integer, Integer>> scoreMap = new ConcurrentHashMap<>();
		
		AppLogger.initLogger();
		Logger logger = AppLogger.getLoggerInstance();
		logger.log(Level.INFO,"Application Logger Initialized.");

		logger.log(Level.INFO, "Starting Application server...");
		try {
			AppConfigurationLoader appConfigurationLoader = new AppConfigurationLoader();
			Properties properties = appConfigurationLoader.loadProperties(AppConstants.APP_CONFIG_FILE);
			int portNumber = Integer.parseInt(properties.getProperty("server.port"));
			int sessionTimeout = Integer.parseInt(properties.getProperty("session.timeout"));
			int sessionCleanupCycle = Integer.parseInt(properties.getProperty("session.cleanupCycle"));
			
			HttpServer server = HttpServer.create(new InetSocketAddress(portNumber), 0);
			server.createContext("/", new RootHandler(sessionMap, scoreMap));
			//server.createContext("/*/loginX", new LoginHandler());
			server.setExecutor(Executors.newCachedThreadPool());
			server.start();
			logger.log(Level.INFO, "Server started.");
			
			logger.log(Level.INFO,"Scheduling Session cleanup thread...");
			SessionInvalidator sessionInvalidator = new SessionInvalidator(sessionMap, sessionTimeout);
			ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
			scheduler.scheduleAtFixedRate(sessionInvalidator, 0, sessionCleanupCycle, TimeUnit.MINUTES);
			logger.log(Level.INFO,"Session cleanup thread scheduled.");

		} catch (IOException e) {
			logger.log(Level.SEVERE, "IO Exception occured while starting server: {0}", e.getMessage());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception occured : {0}", e.getMessage());
		}
	}
}
