package com.app.gaming.utilities;

import static com.app.gaming.constants.AppConstants.HYPHEN;
import static com.app.gaming.constants.AppConstants.RADIX;
import static com.app.gaming.constants.AppConstants.TIMESTAMP_FORMAT;

import java.math.BigInteger;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to invalidate user sessions based on the Session timeout value.
 * 
 */
public class SessionInvalidator implements Runnable {

	private ConcurrentMap<Integer, String> sessionMap;
	private int sessionTimeout;
	private Logger logger = AppLogger.getLoggerInstance();
	
	public SessionInvalidator(ConcurrentMap<Integer, String> sessionMap, int sessionTimeout) {
		this.sessionMap = sessionMap;
		this.sessionTimeout = sessionTimeout;
	}
	
	@Override
	public void run() {
		logger.log(Level.INFO,"Starting Session cleanup...");
		Integer userId;
		String sessionKey;
		BigInteger bigInteger;
		String decodedSessionKey;
		String sessionTimestamp;
		LocalTime sessionTime;
		LocalTime currentTime = LocalTime.now();
		long sessionTimelapse;

		for(Map.Entry<Integer, String> entry : sessionMap.entrySet()) {
			userId = entry.getKey();
			sessionKey = entry.getValue();
			bigInteger = new BigInteger(sessionKey,RADIX);
			decodedSessionKey = new String(bigInteger.toByteArray());
			sessionTimestamp = decodedSessionKey.split(HYPHEN)[1];
			sessionTime = LocalTime.parse(sessionTimestamp, DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT));
			sessionTimelapse = sessionTime.until(currentTime, ChronoUnit.MINUTES);
			if(sessionTimelapse>=sessionTimeout) {
				sessionMap.remove(userId);
				logger.log(Level.INFO, "User Session cleared for user : {0}" , userId);
			}
		}
		logger.log(Level.INFO, "Session cleanup completed.");
	}
}
