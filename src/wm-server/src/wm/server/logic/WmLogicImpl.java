package wm.server.logic;

import java.util.Date;
import java.util.List;

import wm.lib.WmDb;
import wm.lib.WmLogic;
import wm.lib.WmRmi;
import wm.lib.WmState;
import wm.lib.model.Game;
import wm.lib.model.Team;
import wm.lib.model.Tip;
import wm.lib.model.User;

public class WmLogicImpl implements WmRmi {
	private WmDb wmdb;
	
	public WmLogicImpl(WmDb wmdb) {
		this.wmdb = wmdb;
	}

	@Override
	public void add(User user) {
		wmdb.add(user);
	}

	@Override
	public void block(User user) {
		wmdb.block(user);
	}

	@Override
	public List<User> getUsers() {
		return wmdb.getUsers();
	}

	@Override
	public void add(Team team) {
		wmdb.add(team);
	}

	@Override
	public void delete(Team team) {
		wmdb.delete(team);
	}

	@Override
	public void update(Team team) {
		wmdb.update(team);
	}

	@Override
	public List<Team> getTeams() {
		return wmdb.getTeams();
	}

	@Override
	public Game createGame(Team team1, Team team2, String place, Date date, WmState wmState) {
		Game game = new Game(team1.getCountry(), team2.getCountry(), place, date, wmState);
		wmdb.add(game);
		return game;
	}

	@Override
	public void delete(Game game) {
		wmdb.delete(game);
	}

	@Override
	public void update(Game game) {
		wmdb.update(game);
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
