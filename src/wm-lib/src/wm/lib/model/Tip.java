package wm.lib.model;

import java.io.Serializable;

public class Tip implements Serializable {
	private int id;
	private String userId;
	private int gameId;
	private int tipGoalTeam1;
	private int tipGoalTeam2;
	private String tipWmWinnerTeamId;
	private String tipGoalWinnerTeamId;
	private String tipGoalLoserTeamId;
	private String tipSecondPlaceTeamId;
	private String tipWmStateLoserTeamId;
	private String tipZeroGoalsTeamId;
	
	public Tip() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public int getTipGoalTeam1() {
		return tipGoalTeam1;
	}
	public void setTipGoalTeam1(int tipGoalTeam1) {
		this.tipGoalTeam1 = tipGoalTeam1;
	}
	public int getTipGoalTeam2() {
		return tipGoalTeam2;
	}
	public void setTipGoalTeam2(int tipGoalTeam2) {
		this.tipGoalTeam2 = tipGoalTeam2;
	}
	public String getTipWmWinnerTeamId() {
		return tipWmWinnerTeamId;
	}
	public void setTipWmWinnerTeamId(String tipWmWinnerTeamId) {
		this.tipWmWinnerTeamId = tipWmWinnerTeamId;
	}
	public String getTipGoalWinnerTeamId() {
		return tipGoalWinnerTeamId;
	}
	public void setTipGoalWinnerTeamId(String tipGoalWinnerTeamId) {
		this.tipGoalWinnerTeamId = tipGoalWinnerTeamId;
	}
	public String getTipGoalLoserTeamId() {
		return tipGoalLoserTeamId;
	}
	public void setTipGoalLoserTeamId(String tipGoalLoserTeamId) {
		this.tipGoalLoserTeamId = tipGoalLoserTeamId;
	}
	public String getTipSecondPlaceTeamId() {
		return tipSecondPlaceTeamId;
	}
	public void setTipSecondPlaceTeamId(String tipSecondPlaceTeamId) {
		this.tipSecondPlaceTeamId = tipSecondPlaceTeamId;
	}
	public String getTipWmStateLoserTeamId() {
		return tipWmStateLoserTeamId;
	}
	public void setTipWmStateLoserTeamId(String tipWmStateLoserTeamId) {
		this.tipWmStateLoserTeamId = tipWmStateLoserTeamId;
	}
	public String getTipZeroGoalsTeamId() {
		return tipZeroGoalsTeamId;
	}
	public void setTipZeroGoalsTeamId(String tipZeroGoalsTeamId) {
		this.tipZeroGoalsTeamId = tipZeroGoalsTeamId;
	}
}
