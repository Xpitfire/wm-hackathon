package wm.server.logic;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
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
		if(wmdb.getTip(id) == null) {
			return -1;
		}
		int points = 0;
		Tip tip = wmdb.getTip(id);
		HashMap<String, Integer> numGoalsScored = new HashMap<>();
		HashMap<String, Integer> numGamesWon = new HashMap<>();
		HashMap<String, Integer> numGamesLost = new HashMap<>();
		for(Team team: wmdb.getTeams()) {
			numGoalsScored.put(team.getCountry(), 0);
			numGamesWon.put(team.getCountry(), 0);
			numGamesLost.put(team.getCountry(), 0);
		}
		boolean finalHasBeenPlayed = false;
		for(Game game: wmdb.getGames()) {
			// one game has not been finished => error
			if(game.getGoal1() < 0 || game.getGoal2() < 0) {
				return -1;
			}
			String team1 = game.getTeam1Id();
			String team2 = game.getTeam2Id();
			if(game.getGoal1() > game.getGoal2()) {
				numGamesWon.put(team1, numGamesWon.get(team1)+1);
				numGamesLost.put(team2, numGamesWon.get(team2)+1);
				if(game.getWmstate() == WmState.FINAL) {
					finalHasBeenPlayed = true;
					// if correct wm winner has been guessed => 10 points
					if(team1 == tip.getTipWmWinnerTeamId()) {
						points += 10;
					}
					// if correct second place has been guessed => 8 points
					if(team2 == tip.getTipSecondPlaceTeamId()) {
						points += 8;
					}
				}
			}
			if(game.getGoal2() > game.getGoal1()) {
				numGamesWon.put(team2, numGamesWon.get(team2)+1);
				numGamesLost.put(team1, numGamesWon.get(team1)+1);
				if(game.getWmstate() == WmState.FINAL) {
					finalHasBeenPlayed = true;
					// if correct wm winner has been guessed => 10 points
					if(team2 == tip.getTipWmWinnerTeamId()) {
						points += 10;
					}
					// if correct second place has been guessed => 8 points
					if(team1 == tip.getTipSecondPlaceTeamId()) {
						points += 8;
					}
				}
			}
			numGoalsScored.put(team1, numGoalsScored.get(team1)+game.getGoal1());
			numGoalsScored.put(team2, numGoalsScored.get(team2)+game.getGoal2());
		}
		if(!finalHasBeenPlayed) {
			return -1;
		}
		
		boolean maxGamesWon = true, maxGamesLost = true, maxGoalsScored = true, minGoalsScored = true;
		for(Team team: wmdb.getTeams()) {
			int goalsScored = numGoalsScored.get(team.getCountry());
			int gamesWon = numGamesWon.get(team.getCountry());
			int gamesLost = numGamesLost.get(team.getCountry());
			if(numGamesWon.get(team.getCountry()) > numGamesWon.get(tip.getTipMostGamesWonTeamId())) {
				maxGamesWon = false;
			}
			if(numGamesLost.get(team.getCountry()) > numGamesLost.get(tip.getTipMostGamesLostTeamId())) {
				maxGamesLost = false;
			}
			if(numGoalsScored.get(team.getCountry()) > numGoalsScored.get(tip.getTipGoalWinnerTeamId())) {
				maxGoalsScored = false;
			}
			if(numGoalsScored.get(team.getCountry()) < numGoalsScored.get(tip.getTipGoalLoserTeamId())) {
				minGoalsScored = false;
			}
		}
		// if correct team with maximal games won has been guessed => 7 points
		if(maxGamesWon) {
			points += 7;
		}
		// if correct team with maximal games lost has been guessed => 6 points
		if(maxGamesLost) {
			points += 6;
		}
		// if correct team with maximal goals has been guessed => 5 points
		if(maxGoalsScored) {
			points += 5;
		}
		// if correct team with minimal goals has been guessed => 4 points
		if(minGoalsScored) {
			points += 4;
		}
		return points;
	}

}
