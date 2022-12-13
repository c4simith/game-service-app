package com.app.gaming.model;

public class UserScore implements Comparable<UserScore>{

	private Integer userId;
	private Integer score;
	
	public UserScore(Integer userId, Integer score) {
		this.userId = userId;
		this.score = score;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	@Override
	public int compareTo(UserScore userScore) {
		if(this.score.equals(userScore.score)) {
			return userScore.userId.compareTo(this.userId);
		}else {
			return userScore.score.compareTo(this.score);
		}
	}

	
}
