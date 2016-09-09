package swe4.models;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Bet implements Externalizable {
	
	private User user;
	private Team teamA;
	private Team teamB;
	private IntegerProperty id;
	private IntegerProperty goalsTeamA;
	private IntegerProperty goalsTeamB;
	
	
	public Bet() {
		this.id = new SimpleIntegerProperty(-1);
		this.goalsTeamA = new SimpleIntegerProperty(-1);
		this.goalsTeamB = new SimpleIntegerProperty(-1);
	}
	
	public Bet(User user,Team teamA, Team teamB) {
		this();
		
		this.setUser(user);
		this.setTeamA(teamA);
		this.setTeamB(teamB);
	}
	
	public Bet(Integer id, User user,Team teamA, Team teamB, Integer goalsTeamA, Integer goalsTeamB) {
		this(user, teamA, teamB);
		
		this.id.set(id);
		this.goalsTeamA.set(goalsTeamA);
		this.goalsTeamB.set(goalsTeamB);
	}
	
	
	public void clone(Bet game) {
		if(game != null) {
			this.setId(game.getId());
			this.setTeamA(game.getTeamA());
			this.setTeamB(game.getTeamB());
			this.setGoalsTeamA(game.getGoalsTeamA());
			this.setGoalsTeamB(game.getGoalsTeamB());
			
		} else {
			this.setId(-1);
			this.setTeamA(new Team());
			this.setTeamB(new Team());
			this.setGoalsTeamA(-1);
			this.setGoalsTeamB(-1);
		}
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
	
	public IntegerProperty getIdProperty() {
		return id;
	}
	
	public final Integer getId() {
		return id.get();
	}
	
	public final void setId(Integer id) {
		this.id.set(id);
	}
	
	
	
	public IntegerProperty getGoalsTeamAProperty() {
		return goalsTeamA;
	}
	
	public final Integer getGoalsTeamA() {
		return goalsTeamA.get();
	}
	
	public final void setGoalsTeamA(Integer goals) {
		this.goalsTeamA.set(goals);
	}
	
	
	
	public IntegerProperty getGoalsTeamBProperty() {
		return goalsTeamB;
	}
	
	public final Integer getGoalsTeamB() {
		return goalsTeamB.get();
	}
	
	public final void setGoalsTeamB(Integer goals) {
		this.goalsTeamB.set(goals);
	}
	
	
	
	@Override
	public String toString() {
		return "id: " + this.getId() + " " 
				+ this.teamA.getName() + " - " + this.teamB.getName().toString() 
				+ " " + this.getGoalsTeamA() + ":" + this.getGoalsTeamB()
				+ " user: " + this.getUser();			
	}
	
	

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(getUser());
		out.writeObject(getTeamA());
		out.writeObject(getTeamB());
		out.writeInt(getId());
		out.writeInt(getGoalsTeamA());
		out.writeInt(getGoalsTeamB());
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setUser((User) in.readObject());
		setTeamA((Team) in.readObject());
		setTeamB((Team) in.readObject());
		setId(in.readInt());
		setGoalsTeamA(in.readInt());
		setGoalsTeamB(in.readInt());
	}
}
