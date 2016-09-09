package wm.server.logic;

import java.util.Date;
import java.util.List;

import wm.lib.WmLogic;
import wm.lib.WmState;
import wm.lib.model.Game;
import wm.lib.model.Team;
import wm.lib.model.Tip;
import wm.lib.model.User;

public class WmLogicImpl implements WmLogic {

	@Override
	public void add(User user) {
		
	}

	@Override
	public void block(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Team team) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Team team) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Team team) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Team> getTeams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game createGame(Team team1, Team team2, String place, Date date, WmState wmState) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFinalResult(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Game> getGames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Tip tip) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Tip> getTips() {
		// TODO Auto-generated method stub
		return null;
	}

}
