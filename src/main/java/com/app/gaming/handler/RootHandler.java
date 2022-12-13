package com.app.gaming.handler;

import static com.app.gaming.constants.AppConstants.EQUAL_SIGN;
import static com.app.gaming.constants.AppConstants.FWD_SLASH;
import static com.app.gaming.constants.AppConstants.LIMIT_HIGHSCORE;
import static com.app.gaming.constants.AppConstants.STATUSCODE_BAD_REQUEST;
import static com.app.gaming.constants.AppConstants.STATUSCODE_OK;
import static com.app.gaming.constants.AppConstants.STATUSCODE_SERVER_ERROR;
import static com.app.gaming.constants.AppConstants.USECASE_HIGHSCORE;
import static com.app.gaming.constants.AppConstants.USECASE_INVALID;
import static com.app.gaming.constants.AppConstants.USECASE_LOGIN;
import static com.app.gaming.constants.AppConstants.USECASE_UPDATE_SCORE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.app.gaming.exception.InvalidSessionException;
import com.app.gaming.exception.ValidationException;
import com.app.gaming.service.LoginService;
import com.app.gaming.service.ScoreService;
import com.app.gaming.utilities.AppLogger;
import com.app.gaming.utilities.Validator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * 
 * Handler class to handle the application URL end points.
 * Login : http://localhost:8081/4711/login
 * Update Score : http://localhost:8081/2/score?sessionkey=UICSNDK (with the post body: 1500)
 * High score : http://localhost:8081/2/highscorelist
 * 
 */
public class RootHandler implements HttpHandler {

	private Logger logger = AppLogger.getLoggerInstance();
	private ConcurrentMap<Integer, String> sessionMap;
	private ConcurrentMap<Integer, Map<Integer, Integer>> scoreMap;

	public RootHandler(ConcurrentMap<Integer, String> sessionMap,
			ConcurrentMap<Integer, Map<Integer, Integer>> scoreMap) {
		this.sessionMap = sessionMap;
		this.scoreMap = scoreMap;
	}

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		String responseMessage;
		int stausCode;
		String path;
		String usecase;
		Integer userId;
		Integer levelId;
		String sessionKey;
		Integer score;
		String requestBody;

		path = httpExchange.getRequestURI().toString();
		String[] pathArray = path.split(FWD_SLASH);
		try {
			usecase = Validator.validateUriAndGetUsecase(httpExchange);
			switch (usecase) {
			case USECASE_LOGIN:
				userId = Integer.parseInt(pathArray[1]);
				responseMessage = new LoginService().loginAndRetrieveSessionkey(sessionMap, userId);
				stausCode = STATUSCODE_OK;
				break;
			case USECASE_UPDATE_SCORE:
				try {
					levelId = Integer.parseInt(pathArray[1]);
					sessionKey = pathArray[2].split(EQUAL_SIGN)[1];
					userId = Validator.validateSessionAndGetUserId(sessionMap, sessionKey);
					requestBody = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody())).readLine();
					score = Integer.parseInt(requestBody);
					if(score>=0) {
						logger.log(Level.INFO, "Invoking Score service to update user score.");
						responseMessage = new ScoreService().updateScore(scoreMap, userId, levelId, score);
						if(responseMessage.isEmpty()) {
							stausCode = STATUSCODE_OK;
						}else {
							stausCode = STATUSCODE_SERVER_ERROR;
						}
						
					}else {
						stausCode = STATUSCODE_BAD_REQUEST;
						responseMessage = "Score is not in proper format, non-negative number expected";
						logger.log(Level.SEVERE, "Score is not in proper format, non-negative number expected");
					}
				} catch (InvalidSessionException exception) {
					stausCode = STATUSCODE_BAD_REQUEST;
					responseMessage = exception.getMessage();
					logger.log(Level.SEVERE, exception.getMessage());
				} catch (NumberFormatException exception) {
					stausCode = STATUSCODE_BAD_REQUEST;
					responseMessage = "Score is not in proper format, number expected";
					logger.log(Level.SEVERE, "Score is not in proper format, number expected");
				}
				break;
			case USECASE_HIGHSCORE:
				levelId = Integer.parseInt(pathArray[1]);
				responseMessage = new ScoreService().getHighscores(scoreMap, levelId, LIMIT_HIGHSCORE);
				stausCode = STATUSCODE_OK;
				break;
			case USECASE_INVALID:
			default:
				stausCode = STATUSCODE_BAD_REQUEST;
				responseMessage = "Given URL does not match any usecases. Please verify the URL.";
				logger.log(Level.SEVERE, "Given URL does not match any usecases. Please verify the URL.");
				break;
			}

		} catch (ValidationException exception) {
			stausCode = STATUSCODE_BAD_REQUEST;
			responseMessage = exception.getMessage();
			logger.log(Level.SEVERE, exception.getMessage());
		}

		httpExchange.sendResponseHeaders(stausCode, responseMessage.length());
		OutputStream outputStream = httpExchange.getResponseBody();
		outputStream.write(responseMessage.getBytes());
		outputStream.close();
	}
}
