package com.app.gaming.utilities;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Logger class for application logs.
 */
public class AppLogger {
	
	private static Logger logger;
	
	private AppLogger() {
	}
	
	public static void initLogger() {
		logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		try {
			Handler fileHandler = new FileHandler("." + File.separator + "game-service-app.log", true);
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
			logger.addHandler(fileHandler);
		} catch (SecurityException | IOException e) {
			e.getMessage();
		}
	}
	
	public static Logger getLoggerInstance() {
		return logger;
	}
}
