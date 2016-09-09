package swe4.models;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Game implements Externalizable {
	
	private Team teamA;
	private Team teamB;
	
	private IntegerProperty id;
	private BooleanProperty gameFinished;
	private IntegerProperty goalsTeamA;
	private IntegerProperty goalsTeamB;
	
	
	public Game() {
		this.id = new SimpleIntegerProperty(-1);
		this.gameFinished = new SimpleBooleanProperty(false);
		this.goalsTeamA = new SimpleIntegerProperty(-1);
		this.goalsTeamB = new SimpleIntegerProperty(-1);
	}
	
	public Game(Team teamA, Team teamB) {
		this();
		
		this.setTeamA(teamA);
		this.setTeamB(teamB);
	}
	
	public Game(Integer id, Team teamA, Team teamB, Boolean gameFinished, Integer goalsTeamA, Integer goalsTeamB) {
		this(teamA, teamB);
		
		this.id.set(id);
		this.gameFinished.set(gameFinished);
		this.goalsTeamA.set(goalsTeamA);
		this.goalsTeamB.set(goalsTeamB);
	}
	
	
	public void clone(Game game) {
		if(game != null) {
			this.setId(game.getId());
			this.setTeamA(game.getTeamA());
			this.setTeamB(game.getTeamB());
			this.setGameFinished(game.getGameFinished());
			this.setGoalsTeamA(game.getGoalsTeamA());
			this.setGoalsTeamB(game.getGoalsTeamB());
			
		} else {
			this.setId(-1);
			this.setTeamA(new Team());
			this.setTeamB(new Team());
			this.setGameFinished(false);
			this.setGoalsTeamA(-1);
			this.setGoalsTeamB(-1);
		}
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
	
	
	
	
	public BooleanProperty getGameFinishedProperty() {
		return gameFinished;
	}
	
	public final Boolean getGameFinished() {
		return gameFinished.get();
	}
	
	public final void setGameFinished(Boolean finished) {
		this.gameFinished.set(finished);
	}
	
	
	
	
	public String getResult() {
		if(getGameFinished() && goalsTeamA != null && goalsTeamB != null) {
			return getGoalsTeamA().toString() + " : " + getGoalsTeamB().toString();
		}
		return "- : - ";
	}
	
	
	@Override
	public String toString() {
		return "id: " + this.getId() + " " 
				+ this.teamA.getName() + " - " + this.teamB.getName().toString() 
				+ " " + this.getGoalsTeamA() + ":" + this.getGoalsTeamB() 
				+ " finished: " + this.getGameFinished(); 
	}
	
	

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(getTeamA());
		out.writeObject(getTeamB());
		out.writeInt(getId());
		out.writeBoolean(getGameFinished());
		out.writeInt(getGoalsTeamA());
		out.writeInt(getGoalsTeamB());
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		setTeamA((Team) in.readObject());
		setTeamB((Team) in.readObject());
		setId(in.readInt());
		setGameFinished(in.readBoolean());
		setGoalsTeamA(in.readInt());
		setGoalsTeamB(in.readInt());
	}
	
}
