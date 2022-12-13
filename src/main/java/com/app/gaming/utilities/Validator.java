package com.app.gaming.utilities;

import static com.app.gaming.constants.AppConstants.EQUAL_SIGN;
import static com.app.gaming.constants.AppConstants.FWD_SLASH;
import static com.app.gaming.constants.AppConstants.USECASE_HIGHSCORE;
import static com.app.gaming.constants.AppConstants.USECASE_INVALID;
import static com.app.gaming.constants.AppConstants.USECASE_LOGIN;
import static com.app.gaming.constants.AppConstants.USECASE_UPDATE_SCORE;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import com.app.gaming.exception.InvalidSessionException;
import com.app.gaming.exception.ValidationException;
import com.sun.net.httpserver.HttpExchange;


/**
 * Validation class to perform various validation on input URL, parameter etc.
 */
public class Validator {
	
	private Validator() {
	}

	/**
	 * Validates the input URL and returns the corresponding use case.
	 * @param httpExchange
	 * @return use case : Login/Update Score/High score
	 * @throws ValidationException
	 */
	public static String validateUriAndGetUsecase(HttpExchange httpExchange) throws ValidationException {
		
		String usecase;
		int userId;
		int levelId;
		String sessionKey;
		String path = httpExchange.getRequestURI().toString();
		String[] pathArray = path.split(FWD_SLASH);
		
		if(pathArray[2].equals("login")) {
			if(!httpExchange.getRequestMethod().equals("GET")) {
				throw new ValidationException("Invlaid rquest method, GET expected");
			}
			try {
				userId = Integer.parseInt(pathArray[1]);
				if(userId>0) {
					usecase = USECASE_LOGIN;
				}else {
					throw new ValidationException("UserId is not in proper format, non-negative number expected");
				}
			}catch(NumberFormatException exception) {
				throw new ValidationException("UserId is not in proper format, non-negative number expected");
			}
		}else if(pathArray[2].startsWith("score?sessionkey=")) {
			if(!httpExchange.getRequestMethod().equals("POST")) {
				throw new ValidationException("Invlaid rquest method, POST expected");
			}
			try {
				levelId = Integer.parseInt(pathArray[1]);
				sessionKey = pathArray[2].split(EQUAL_SIGN)[1];
				if(levelId<=0) {
					throw new ValidationException("LevelId is not in proper format, non-negative number expected");
				}else if (sessionKey == null || sessionKey.isEmpty() || sessionKey.isBlank()) {
					throw new ValidationException("Session Key is empty. A proper session key is expected");
				}else {
					usecase = USECASE_UPDATE_SCORE;
				}
			}catch(NumberFormatException exception) {
				throw new ValidationException("LevelId is not in proper format, non-negative number expected");
			}catch(Exception exception) {
				throw new ValidationException("Error occured, unable to process Session Key");
			}
		}else if(pathArray[2].equals("highscorelist")) {
			if(!httpExchange.getRequestMethod().equals("GET")) {
				throw new ValidationException("Invlaid rquest method, GET expected");
			}
			try {
				levelId = Integer.parseInt(pathArray[1]);
				if(levelId>0) {
					usecase = USECASE_HIGHSCORE;
				}else {
					throw new ValidationException("LevelId is not in proper format, non-negative number expected");
				}
			}catch(NumberFormatException exception) {
				throw new ValidationException("LevelId is not in proper format, non-negative number expected");
			}
		}else {
			usecase = USECASE_INVALID;
		}
		return usecase;
	}
	
	/**
	 * Validate the input session key and returns the corresponding userId if session exists.
	 * @param sessionMap
	 * @param sessionKey
	 * @return userId
	 * @throws InvalidSessionException
	 */
	public static Integer validateSessionAndGetUserId(ConcurrentMap<Integer, String> sessionMap, String sessionKey) throws InvalidSessionException{
		int userId=0;
		for(Map.Entry<Integer, String> entry : sessionMap.entrySet()) {
			if(entry.getValue().equals(sessionKey)) {
				userId = entry.getKey();
				break;
			}
		}
		if(userId == 0) {
			throw new InvalidSessionException("Given session key does not exist or expired.");
		}else {
			return userId;
		}
		
	}
}
