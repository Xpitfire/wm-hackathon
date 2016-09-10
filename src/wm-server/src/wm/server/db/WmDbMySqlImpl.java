package wm.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import wm.lib.WmDb;
import wm.lib.model.Game;
import wm.lib.model.Team;
import wm.lib.model.Tip;
import wm.lib.model.User;
import wm.lib.model.WmState;

public class WmDbMySqlImpl implements WmDb {

	private static final String DB_URL = "127.0.0.1";
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "dbo";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

	public WmDbMySqlImpl() {
	}

	@Override
	public void add(User user) {
		executeSqlStatement(
				"INSERT INTO User VALUES("
						+ " '" + user.getUsername() + "',"
						+ " '" + user.getPasswordHash() + "', "
						+ " '" + user.getFirstName() + "', "
						+ " '" + user.getLastName() + "', "
						+ " " + user.isActive()
						+ ");");
	}

	@Override
	public void block(User user) {
		executeSqlStatement(
				"UPDATE User SET "
						+ "isActive = " + false
						+ " WHERE username LIKE '" + user.getUsername() + "';");
	}

	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		ResultSet result = executeSqlSelect("SELECT * FROM User");
		try {
			while (result.next()) {
				User user = new User(
						result.getString("username"), 
						result.getString("firstName"),
						result.getString("lastName"));
				user.setPasswordHash(result.getString("passwordHash"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void add(Team team) {
		executeSqlStatement(
				"INSERT INTO Team VALUES("
						+ " '" + team.getCountry() + "',"
						+ " '" + team.getName() + "' "
						+ ");");
	}

	@Override
	public void delete(Team team) {
		executeSqlStatement(
				"DELETE FROM Team WHERE country LIKE '" 
						+ team.getCountry() + "';");
	}

	@Override
	public void update(Team team) {
		executeSqlStatement(
				"UPDATE Team SET "
						+ "name = '" + team.getName() + "'"
						+ " WHERE country LIKE '" + team.getCountry() + "';");
	}

	@Override
	public List<Team> getTeams() {
		List<Team> teams = new ArrayList<>();
		ResultSet result = executeSqlSelect("SELECT * FROM Team");
		try {
			while (result.next()) {
				Team team = new Team(
						result.getString("country"), 
						result.getString("name"));
				teams.add(team);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teams;
	}
	
	@Override
	public int add(Game game) {
		return executeSqlStatement(
				"INSERT INTO Game (team1Id, team2Id, place, date, goal1, goal2, wmstate) VALUES("
						+ " '" + game.getTeam1Id() + "',"
						+ " '" + game.getTeam2Id() + "',"
						+ " '" + game.getPlace() + "',"
						+ " '" + DATE_FORMATTER.format(game.getDate()) + "',"
						+ " '" + game.getGoal1() + "',"
						+ " '" + game.getGoal2() + "',"
						+ " '" + game.getWmstate().getId() + "' "
						+ ");");
	}

	@Override
	public void delete(Game game) {
		executeSqlStatement(
				"DELETE FROM Game WHERE id = " 
						+ game.getId() + ";");
	}

	@Override
	public void update(Game game) {
		executeSqlStatement(
				"UPDATE Game SET "
						+ "team1Id = '" + game.getTeam1Id() + "', "
						+ "team2Id = '" + game.getTeam2Id() + "', "
						+ "place = '" + game.getPlace() + "', "
						+ "date = '" + DATE_FORMATTER.format(game.getDate()) + "', "
						+ "goal1 = '" + game.getGoal1() + "', "
						+ "goal2 = '" + game.getGoal2() + "', "
						+ "wmstate = '" + game.getWmstate().getId() + "'"
						+ " WHERE id = " + game.getId() + ";");
	}

	@Override
	public List<Game> getGames() {
		List<Game> games = new ArrayList<>();
		ResultSet result = executeSqlSelect("SELECT * FROM Game");
		try {
			while (result.next()) {
				Game game = new Game(
						result.getString("team1Id"), 
						result.getString("team2Id"),
						result.getString("place"),
						result.getDate("date"),
						WmState.toWmState(result.getInt("wmState")));
				game.setId(result.getInt("id"));
				game.setGoal1(result.getInt("goal1"));
				game.setGoal2(result.getInt("goal2"));
				games.add(game);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return games;
	}

	@Override
	public int add(Tip tip) {
		return executeSqlStatement(
				"INSERT INTO Tip (userId, gameId, tipGoalTeam1, tipGoalTeam2, tipWmWinnerTeamId, tipSecondPlaceTeamId, tipGoalWinnerTeamId, tipGoalLoserTeamId, tipMostGamesWonTeamId, tipMostGamesLostTeamId) VALUES("
						+ " '" + tip.getUserId() + "',"
						+ " '" + tip.getGameId() + "',"
						+ " '" + tip.getTipGoalTeam1() + "',"
						+ " '" + tip.getTipGoalTeam2() + "',"
						+ " '" + tip.getTipWmWinnerTeamId() + "',"
						+ " '" + tip.getTipSecondPlaceTeamId() + "',"
						+ " '" + tip.getTipGoalWinnerTeamId() + "' "
						+ " '" + tip.getTipGoalLoserTeamId() + "' "
						+ " '" + tip.getTipMostGamesWonTeamId() + "' "
						+ " '" + tip.getTipMostGamesLostTeamId() + "' "
						+ ");");
	}

	@Override
	public List<Tip> getTips() {
		List<Tip> tips = new ArrayList<>();
		ResultSet result = executeSqlSelect("SELECT * FROM Tip");
		try {
			while (result.next()) {
				Tip tip = new Tip();
				tip.setId(result.getInt("id"));
				tip.setUserId(result.getString("userId"));
				tip.setGameId(result.getInt("gameId"));
				tip.setTipGoalTeam1(result.getInt("tipGoalTeam1"));
				tip.setTipGoalTeam2(result.getInt("tipGoalTeam2"));
				tip.setTipWmWinnerTeamId("tipWmWinnerTeamId");
				tip.setTipSecondPlaceTeamId("tipSecondPlaceTeamId");
				tip.setTipGoalWinnerTeamId("tipGoalWinnerTeamId");
				tip.setTipGoalLoserTeamId("tipGoalLoserTeamId");
				tip.setTipMostGamesWonTeamId("tipMostGamesWonTeamId");
				tip.setTipMostGamesLostTeamId("tipMostGamesLostTeamId");
				tips.add(tip);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tips;
	}

	@Override
	public User getUser(String username) {
		List<User> users = getUsers();
		for (User u : users) {
			if (u.getUsername().equals(username)) return u;
		}
		return null;
	}

	@Override
	public Team getTeam(String countryId) {
		List<Team> teams = getTeams();
		for (Team t : teams) {
			if (t.getCountry().equals(countryId)) return t;
		}
		return null;
	}

	@Override
	public Game getGame(int id) {
		List<Game> games = getGames();
		for (Game g : games) {
			if (g.getId() == id) return g;
		}
		return null;
	}

	@Override
	public Tip getTip(int id) {
		List<Tip> tips = getTips();
		for (Tip t : tips) {
			if (t.getId() == id) return t;
		}
		return null;
	}
		
	private int executeSqlStatement(String sql) {
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				int newId = rs.getInt(1);
				return newId;
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	private ResultSet executeSqlSelect(String sql) {
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			return statement.executeQuery(sql);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://" 
				+ DB_URL + ":" 
				+ DB_PORT + "/" 
				+ DB_NAME, DB_USER, DB_PASSWORD);
	}

}
