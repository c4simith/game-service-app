package com.app.gaming.utilities;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to load the Application configuration file.
 * Loads the default value if error loading the file.
 */
public class AppConfigurationLoader {

	public Properties loadProperties(String filePath) {
		
		Logger logger = AppLogger.getLoggerInstance();
		Properties properties = new Properties();
		
		try ( InputStream inputStream = new FileInputStream(filePath);) {
			properties.load(inputStream);
			logger.log(Level.INFO, "Loaded properties from Application configuration file.");
		}catch (Exception e) {
			logger.log(Level.INFO, "Unable to load Application configuration file. Configuring default values.");
			properties.setProperty("server.port","8081");
			properties.setProperty("session.timeout","10");
			properties.setProperty("session.cleanupCycle","5");
		}
		return properties;
	}
}
