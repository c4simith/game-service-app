package com.app.gaming.constants;

import java.io.File;

/**
 * 
 * Class to hold all the application constants.
 *
 */
public final class AppConstants {

	private AppConstants() {
	}
	public static final String APP_CONFIG_FILE = "." + File.separator +  "AppConfig.properties";
	public static final String USECASE_LOGIN = "LOGIN";
	public static final String USECASE_UPDATE_SCORE = "SCORE";
	public static final String USECASE_HIGHSCORE = "HIGHSCORE";
	public static final String USECASE_INVALID = "INVALID";
	public static final String FWD_SLASH = "/";
	public static final String HYPHEN = "-";
	public static final String EQUAL_SIGN = "=";
	public static final String COMMA = ",";
	public static final String TIMESTAMP_FORMAT = "HHmmss";
	public static final int LIMIT_HIGHSCORE = 15;
	public static final int RADIX = 36;
	public static final int STATUSCODE_OK = 200;
	public static final int STATUSCODE_BAD_REQUEST = 400;
	public static final int STATUSCODE_SERVER_ERROR = 500;
}
