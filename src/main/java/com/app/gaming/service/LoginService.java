package com.app.gaming.service;

import static com.app.gaming.constants.AppConstants.HYPHEN;
import static com.app.gaming.constants.AppConstants.RADIX;
import static com.app.gaming.constants.AppConstants.TIMESTAMP_FORMAT;

import java.math.BigInteger;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentMap;

/**
 * Service class to handle the business logic for LOGIN use case.
 */
public class LoginService {

	/**
	 * Login and retrieve the session key based on userId.
	 * @param sessionMap
	 * @param userId
	 * @return encodedSessionKey
	 */
	public String loginAndRetrieveSessionkey(ConcurrentMap<Integer, String> sessionMap, Integer userId) {
		String sessionKey;
		String encodedSessionKey;
		
		if(sessionMap.containsKey(userId)) {
			encodedSessionKey = sessionMap.get(userId);
		}else {
			LocalTime currentTime = LocalTime.now();
			String timestamp = currentTime.format(DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT));
			sessionKey = userId + HYPHEN + timestamp;
			encodedSessionKey = new BigInteger(sessionKey.getBytes()).toString(RADIX);
			sessionMap.put(userId, encodedSessionKey);
		}
		return encodedSessionKey;
	}
}
