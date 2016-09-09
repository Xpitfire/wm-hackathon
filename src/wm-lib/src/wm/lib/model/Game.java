package wm.lib.model;

import java.io.Serializable;
import java.util.Date;

import wm.lib.WmState;

public class Game implements Serializable {
	private int id;
	private String team1Id;
	private String team2Id;
	private String place;
	private Date date;
	private int goal1;
	private int goal2;
	private WmState wmstate;
	
	public Game() {
	}
	
	public Game(String team1Id, String team2Id, String place, Date date, WmState wmState) {
		super();
		this.team1Id = team1Id;
		this.team2Id = team2Id;
		this.place = place;
		this.date = date;
		this.wmstate = wmState;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTeam1Id() {
		return team1Id;
	}
	public void setTeam1Id(String team1Id) {
		this.team1Id = team1Id;
	}
	public String getTeam2Id() {
		return team2Id;
	}
	public void setTeam2Id(String team2Id) {
		this.team2Id = team2Id;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getGoal1() {
		return goal1;
	}
	public void setGoal1(int goal1) {
		this.goal1 = goal1;
	}
	public int getGoal2() {
		return goal2;
	}
	public void setGoal2(int goal2) {
		this.goal2 = goal2;
	}
	public WmState getWmstate() {
		return wmstate;
	}
	public void setWmstate(WmState wmstate) {
		this.wmstate = wmstate;
	}
}
