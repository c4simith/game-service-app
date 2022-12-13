package com.app.gaming.service;

import static com.app.gaming.constants.AppConstants.COMMA;
import static com.app.gaming.constants.AppConstants.EQUAL_SIGN;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentMap;

import com.app.gaming.model.UserScore;

/**
 * Service class to handle the business logic for SCORE use cases.
 */
public class ScoreService {

	/**
	 * Updates the level score for user.
	 * @param scoreMap
	 * @param userId
	 * @param levelId
	 * @param score
	 * @return response message; Empty for success scenario.
	 */
	public String updateScore(ConcurrentMap<Integer, Map<Integer, Integer>> scoreMap, Integer userId, Integer levelId, Integer score) {
		
		String response;
		if(scoreMap==null) {
			response = "Invalid Score map, null pointer exception occured.";
		}else {
			if(scoreMap.get(userId)!=null) {
				Map<Integer, Integer> userScores = scoreMap.get(userId);
				userScores.put(levelId, score);
				scoreMap.put(userId, userScores);
				response="";
			}else {
				Map<Integer, Integer> newUserScores = new HashMap<>();
				newUserScores.put(levelId, score);
				scoreMap.put(userId, newUserScores);
				response="";
			}
		}
		return response;
	}

	/**
	 * Get the high score list for given level.
	 * @param scoreMap
	 * @param inputLevelId
	 * @param limit
	 * @return Comma separated high score list as userId=score
	 */
	public String getHighscores(ConcurrentMap<Integer, Map<Integer, Integer>> scoreMap, Integer inputLevelId, Integer limit) {

		String highScoreList;
		TreeSet<UserScore> sortedScores = new TreeSet<>();
		Map<Integer, Integer> userScores;
		Integer currentScore;
		Integer userId;
		Integer currentLevelId;
		
		for (Map.Entry<Integer, Map<Integer, Integer>> scoreMapEntry : scoreMap.entrySet()) {
			userId = scoreMapEntry.getKey();
			userScores = scoreMapEntry.getValue();
			for (Map.Entry<Integer, Integer> userScoreEntry : userScores.entrySet()) {
				currentLevelId = userScoreEntry.getKey();
				currentScore = userScoreEntry.getValue();
				
				if(!currentLevelId.equals(inputLevelId)) {
					continue;
				}
				
				if ((sortedScores.size() < limit)) {
					UserScore userScoreObject = new UserScore(userId, currentScore);
					sortedScores.add(userScoreObject);
				} else if (sortedScores.size() == limit) {
					UserScore lowestUserScoreObject = sortedScores.last();
					if(currentScore > lowestUserScoreObject.getScore()) {
						sortedScores.remove(lowestUserScoreObject);
						UserScore newUserScoreObject = new UserScore(userId, currentScore);
						sortedScores.add(newUserScoreObject);
					}
				} 
			}
		}
		
		highScoreList = "";
		for (UserScore userScore : sortedScores) {
			if(highScoreList.isEmpty()) {
				highScoreList = userScore.getUserId() + EQUAL_SIGN + userScore.getScore();
			} else {
				highScoreList = highScoreList + COMMA + userScore.getUserId() + EQUAL_SIGN + userScore.getScore();
			}
		}
		return highScoreList;
	}
}
