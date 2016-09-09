package swe4.models;

public class Game {
	
	private Team teamA;
	private Team teamB;
	
	private Boolean gameFinished;
	private Integer goalsTeamA;
	private Integer goalsTeamB;
	
	
	public Game() {}
	public Game(Team teamA, Team teamB) {
		this.setTeamA(teamA);
		this.setTeamB(teamB);
	}
	public Team getTeamA() {
		return teamA;
	}
	public void setTeamA(Team teamA) {
		this.teamA = teamA;
	}
	public Team getTeamB() {
		return teamB;
	}
	public void setTeamB(Team teamB) {
		this.teamB = teamB;
	}
	
	public String getResult() {
		if(getGameFinished() && goalsTeamA != null && goalsTeamB != null) {
			return getGoalsTeamA().toString() + " : " + getGoalsTeamB().toString();
		}
		return "not played yet";
	}
	
	public Integer getGoalsTeamA() {
		return goalsTeamA;
	}
	
	public void setGoalsTeamA(Integer goalsTeamA) {
		this.goalsTeamA = goalsTeamA;
	}
	
	public Integer getGoalsTeamB() {
		return goalsTeamB;
	}
	
	public void setGoalsTeamB(Integer goalsTeamB) {
		this.goalsTeamB = goalsTeamB;
	}
	public Boolean getGameFinished() {
		return gameFinished;
	}
	public void setGameFinished(Boolean gameFinished) {
		this.gameFinished = gameFinished;
	}
	
	@Override
	public String toString() {
		return this.teamA.getName() + " - " + this.teamB.getName().toString(); 
	}
	
}
