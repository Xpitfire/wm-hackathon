package wm.server.logic;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import wm.lib.WmDb;
import wm.lib.WmRmi;
import wm.lib.model.Game;
import wm.lib.model.Team;
import wm.lib.model.Tip;
import wm.lib.model.User;
import wm.lib.model.WmState;

public class WmLogicImpl implements WmRmi {
	private WmDb wmdb;
	
	public WmLogicImpl(WmDb wmdb) {
		this.wmdb = wmdb;
	}

	@Override
	public boolean createUser(String username, String passwordHash, String firstName, String lastName)
			throws RemoteException {
		if(wmdb.getUser(username) != null || username.length() < 1 || passwordHash.length() < 1 || firstName.length() < 1  || lastName.length() < 1) {
			return false;
		}
		wmdb.add(new User(username, passwordHash, firstName, lastName));
		return true;
	}

	@Override
	public boolean blockUser(String username) throws RemoteException {
		if(wmdb.getUser(username) == null || !wmdb.getUser(username).isActive()) {
			return false;
		}
		wmdb.block(wmdb.getUser(username));
		return true;
	}

	@Override
	public List<User> getUsers() throws RemoteException {
		return wmdb.getUsers();
	}

	@Override
	public boolean authenticateUser(String username, String passwordHash) throws RemoteException {
		if(wmdb.getUser(username) == null || wmdb.getUser(username).getPasswordHash() != passwordHash) {
			return false;
		}
		return true;
	}

	@Override
	public boolean createTeam(String country, String name) throws RemoteException {
		country = country.toUpperCase();
		if(wmdb.getTeam(country) != null  || country.length() < 1 || name.length() < 1) {
			return false;
		}
		wmdb.add(new Team(country, name));
		return true;
	}

	@Override
	public boolean deleteTeam(String country) throws RemoteException {
		country = country.toUpperCase();
		country = country.toUpperCase();
		if(wmdb.getTeam(country) == null) {
			return false;
		}
		wmdb.delete(wmdb.getTeam(country));
		return true;
	}

	@Override
	public boolean updateTeam(String country, String name) throws RemoteException {
		country = country.toUpperCase();
		if(wmdb.getTeam(country) == null || name.length() < 1) {
			return false;
		}
		wmdb.update(new Team(country, name));
		return true;
	}

	@Override
	public List<Team> getTeams() throws RemoteException {
		return wmdb.getTeams();
	}

	@Override
	public int createGame(String team1, String team2, String place, Date date, WmState wmState) throws RemoteException {
		team1 = team1.toUpperCase();
		team2 = team2.toUpperCase();
		if(wmdb.getTeam(team1) == null || wmdb.getTeam(team2) == null || place.length() < 1 || date == null || wmState == null) {
			return -1;
		}
		return wmdb.add(new Game(team1, team2, place, date, wmState));
	}

	@Override
	public boolean deleteGame(int id) throws RemoteException {
		if(wmdb.getGame(id) == null) {
			return false;
		}
		wmdb.delete(wmdb.getGame(id));
		return true;
	}

	@Override
	public boolean updateGame(int id, String team1, String team2, String place, Date date, WmState wmState) {
		team1 = team1.toUpperCase();
		team2 = team2.toUpperCase();
		if(wmdb.getGame(id) == null || wmdb.getTeam(team2) == null || place.length() < 1 || date == null || wmState == null) {
			return false;
		}
		wmdb.update(new Game(team1, team2, place, date, wmState));
		return true;
	}

	@Override
	public boolean setFinalResult(int id, int goal1, int goal2) throws RemoteException {
		if(wmdb.getGame(id) == null) {
			return false;
		}
		Game game = wmdb.getGame(id);
		game.setGoal1(goal1);
		game.setGoal2(goal2);
		wmdb.update(game);
		return true;
	}

	@Override
	public List<Game> getGames() throws RemoteException {
		return wmdb.getGames();
	}

	@Override
	public int addTip(Tip tip) throws RemoteException {
		if(wmdb.getGame(tip.getGameId()) == null || wmdb.getGame(tip.getGameId()).getDate().getTime() < System.currentTimeMillis()) {
			return -1;
		}
		return wmdb.add(tip);
	}

	@Override
	public List<Tip> getTips() throws RemoteException {
		return wmdb.getTips();
	}

	@Override
	public int evaluateTip(int id) throws RemoteException {
		if(wmdb.getTip(id) == null) {
			return -1;
		}
		Tip tip = wmdb.getTip(id);
		int goal1 = wmdb.getGame(tip.getGameId()).getGoal1();
		int goal2 = wmdb.getGame(tip.getGameId()).getGoal2();
		if(goal1 < 0 || goal2 < 0) {
			return -1;
		}
		int points = 0;
		// if correct winner has been guessed => 1 point
		if((goal1 > goal2 && tip.getTipGoalTeam1() > tip.getTipGoalTeam2())
				|| (goal1 < goal2 && tip.getTipGoalTeam1() < tip.getTipGoalTeam2())
				|| (goal1 == goal2 && tip.getTipGoalTeam1() == tip.getTipGoalTeam2())) {
			points++;
		}
		// if correct tendency has been guessed => 1 additional point
		if(goal1 - goal2 == tip.getTipGoalTeam1() - tip.getTipGoalTeam2()) {
			points++;
		}
		// if correct score has been guessed => 2 additional points
		if(goal1 == tip.getTipGoalTeam1() && goal2 == tip.getTipGoalTeam2()) {
			points += 2;
		}
		return points;
	}
	

	@Override
	public int evaluateBonusQuestions(int id) throws RemoteException {
		return 0;
	}

}
