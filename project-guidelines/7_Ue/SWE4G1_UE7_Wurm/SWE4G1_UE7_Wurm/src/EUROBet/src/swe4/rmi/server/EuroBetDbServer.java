package swe4.rmi.server;

import java.sql.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import swe4.models.Bet;
import swe4.models.Game;
import swe4.models.Team;
import swe4.models.User;
import swe4.rmi.client.EuroBetDbFactory;
import swe4.util.Util;



public class EuroBetDbServer implements EuroBetDb {


	private static final String DB_URL = "127.0.0.1";
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "EuroBetDb";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";

	private List<EuroBetDbFactory> clientList = new ArrayList<>();


	public EuroBetDbServer() throws RemoteException {
		super();
	}



	@Override
	public synchronized List<User> getUsers() throws RemoteException {

		List<User> users = new ArrayList<>();
		ResultSet result = executeSqlSelect("SELECT * FROM users");

		System.out.println("--> getUsers");
		try {
			while(result.next()) {

				User user = new User(
						result.getInt("idusers"),
						result.getBoolean("active"),
						result.getString("name"),
						result.getString("password")
				);
				users.add(user);
				System.out.println("     -> User: " + user.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return users;
	}



	@Override
	public void updateUser(User user) throws RemoteException {

		System.out.println("--> updateUser");
		System.out.println("     -> User: " + user.toString());

		executeSqlUpdate(
				"UPDATE users SET"
						+ " name = '" + user.getName() + "',"
						+ " password = '" + user.getPassword() + "', "
						+ " active = " + user.getActive()
					+ " WHERE idusers = " + user.getId()
					+ ";");

		notifiyOnUserUpdated(user);
	}


	@Override
	public Integer addUser(User user) throws RemoteException {

		System.out.println("--> addUser");
		System.out.println("     -> User: " + user.toString());

		Integer newId = executeSqlUpdate(
				"INSERT INTO users VALUES("
						+ " null, "
						+ " '" + user.getName() + "',"
						+ " '" + user.getPassword() + "', "
						+ " " + user.getActive()
					+ ");");
		user.setId(newId);
		notifiyOnUserAdded(user);
		return user.getId();
	}

	@Override
	public Integer addGroup(Character group) throws RemoteException {
		String groupStr = group.toString();
		System.out.println("--> addGroup");
		System.out.println("     -> group: " + groupStr);

		Integer newId = executeSqlUpdate(
				"INSERT INTO groups VALUES("
						+ " null, "
						+ " '" + groupStr + "'"
					+ ");");

		return newId;
	}

	@Override
	public List<String> getGroups() throws RemoteException {
		List<String> groups = new ArrayList<>();
		ResultSet result = executeSqlSelect("SELECT * FROM groups");

		System.out.println("--> getGroups");
		try {
			while(result.next()) {

				groups.add(result.getString("name"));
				System.out.println("     -> Group: " + result.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return groups;
	}


	@Override
	public List<Team> getTeams() throws RemoteException {
		List<Team> teams = new ArrayList<>();


		ResultSet result = executeSqlSelect("SELECT * FROM teams");

		System.out.println("--> getTeams");


		try {
			while(result.next()) {

				// get group

				String groupId = result.getString("groupId");
				ResultSet groupResult = executeSqlSelect("SELECT * FROM groups WHERE Idgroups = " + groupId);
				String groupName = "";
				while(groupResult.next()) {
					groupName = groupResult.getString("name");
				}

				Team team = new Team(
						result.getInt("idteams"),
						result.getString("name"),
						groupName
				);
				teams.add(team);
				System.out.println("     -> Team: " + team.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return teams;
	}



	@Override
	public void updateTeam(Team team) throws RemoteException {
		System.out.println("--> updateTeam");
		System.out.println("     -> Team: " + team.toString());

		Integer groupId = getGroupIdByName(team.getGroup());
		executeSqlUpdate(
				"UPDATE teams SET"
						+ " name = '" + team.getName() + "',"
						+ " groupId = " + groupId
					+ " WHERE idteams = " + team.getId()
					+ ";");

		notifiyOnTeamUpdated(team);
	}

	@Override
	public Integer addTeam(Team team) throws RemoteException {
		System.out.println("--> addTeam");
		System.out.println("     -> Team: " + team.toString());

		Integer groupId;

		if(team.getGroup().equals("")) {
			groupId = 1; // default group
		} else {
			groupId = getGroupIdByName(team.getGroup());
		}

		Integer newId = executeSqlUpdate(
				"INSERT INTO teams VALUES("
						+ " null, "
						+ " '" + team.getName() + "',"
						+ " " +  groupId
					+ ");");

		team.setId(newId);
		notifiyOnTeamAdded(team);
		return team.getId();
	}


	private Integer getGroupIdByName(String name) {
		ResultSet groupResult = executeSqlSelect("SELECT * FROM groups WHERE name = '" + name + "'");
		Integer id = -1;
		try {
			while(groupResult.next()) {
				id = groupResult.getInt("idgroups");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}



	@Override
	public List<Game> getGames() throws RemoteException {
		List<Game> games = new ArrayList<>();
		ResultSet result = executeSqlSelect("SELECT * FROM games");
		System.out.println("--> getGames");
		try {
			while(result.next()) {

				// get teams
				Team teamA = getTeamById(result.getInt("teamAId"));
				Team teamB = getTeamById(result.getInt("teamBId"));

				Game game = new Game(
						result.getInt("idgames"),
						teamA,
						teamB,
						result.getBoolean("finished"),
						result.getInt("goalsTeamA"),
						result.getInt("goalsTeamB")
				);
				games.add(game);
				System.out.println("     -> Game: " + game.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return games;
	}


	@Override
	public void updateGame(Game game) throws RemoteException {
		System.out.println("--> updateGame");
		System.out.println("     -> Game: " + game.toString());


		executeSqlUpdate(
				"UPDATE games SET"
						+ " teamAId = " + game.getTeamA().getId() + ","
						+ " teamBId = " + game.getTeamB().getId() + ","
						+ " goalsTeamA = " + game.getGoalsTeamA() + ","
						+ " goalsTeamB = " + game.getGoalsTeamB() + ","
						+ " finished = " + game.getGameFinished()
					+ " WHERE idgames = " + game.getId()
					+ ";");

		notifiyOnGameUpdated(game);
	}



	@Override
	public Integer addGame(Game game) throws RemoteException {
		System.out.println("--> addGame");
		System.out.println("     -> game: " + game.toString());
		Integer newId = executeSqlUpdate(
				"INSERT INTO games VALUES("
						+ " null, "
						+ " " + game.getTeamA().getId() + ","
						+ " " + game.getTeamB().getId() + ","
						+ " " + game.getGoalsTeamA() + ","
						+ " " + game.getGoalsTeamB() + ","
						+ " " + game.getGameFinished()
					+ ");");

		game.setId(newId);
		notifiyOnGameAdded(game);
		return game.getId();
	}

	@Override
	public void removeAllBets() throws RemoteException {
		executeSqlUpdate("DELETE FROM bets WHERE idbets >= 0");
		System.out.println("--> removeAllBets");

	}

	@Override
	public List<Bet> getBets() throws RemoteException {
		List<Bet> bets = new ArrayList<>();
		ResultSet result = executeSqlSelect("SELECT * FROM bets");
		System.out.println("--> getBets");
		try {
			while(result.next()) {

				// get relationships
				Team teamA = getTeamById(result.getInt("teamAId"));
				Team teamB = getTeamById(result.getInt("teamBId"));
				User user = getUserById(result.getInt("userId"));

				Bet bet = new Bet(
						result.getInt("idbets"),
						user,
						teamA,
						teamB,
						result.getInt("goalsTeamA"),
						result.getInt("goalsTeamB")
				);
				bets.add(bet);
				System.out.println("     -> Bet: " + bet.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return bets;
	}

	@Override
	public Integer addBet(Bet bet) throws RemoteException {
		System.out.println("--> addBet");
		System.out.println("     -> bet: " + bet.toString());
		Integer newId = executeSqlUpdate(
				"INSERT INTO bets VALUES("
						+ " null, "
						+ " " + bet.getUser().getId() + ","
						+ " " + bet.getTeamA().getId() + ","
						+ " " + bet.getTeamB().getId() + ","
						+ " " + bet.getGoalsTeamA() + ","
						+ " " + bet.getGoalsTeamB()
					+ ");");

		bet.setId(newId);
		notifiyOnBetAdded(bet);
		return bet.getId();
	}


	@Override
	public void updateBet(Bet bet) throws RemoteException {
		System.out.println("--> updateBet");
		System.out.println("     -> Bet: " + bet.toString());


		executeSqlUpdate(
				"UPDATE bets SET"
						+ " userId = " + bet.getUser().getId() + ","
						+ " teamAId = " + bet.getTeamA().getId() + ","
						+ " teamBId = " + bet.getTeamB().getId() + ","
						+ " goalsTeamA = " + bet.getGoalsTeamA() + ","
						+ " goalsTeamB = " + bet.getGoalsTeamB()
					+ " WHERE idbets = " + bet.getId()
					+ ";");

		notifiyOnBetUpdated(bet);
	}


	@Override
	public void removeAllUsers() throws RemoteException {
		executeSqlUpdate("DELETE FROM users WHERE idusers >= 0");
		System.out.println("--> removeAllUsers");
	}



	@Override
	public void removeAllGroups() throws RemoteException {
		executeSqlUpdate("DELETE FROM groups WHERE idgroups >= 0");
		System.out.println("--> removeAllGroups");
	}



	@Override
	public void removeAllTeams() throws RemoteException {
		executeSqlUpdate("DELETE FROM teams WHERE idteams >= 0");
		System.out.println("--> removeAllTeams");
	}



	@Override
	public void removeAllGames() throws RemoteException {
		executeSqlUpdate("DELETE FROM games WHERE idgames >= 0");
		System.out.println("--> removeAllGames");
	}



	private String getGroupNameById(Integer id) {
		ResultSet groupResult = executeSqlSelect("SELECT * FROM groups WHERE idgroups = " + id );
		try {
			while(groupResult.next()) {
				return  groupResult.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	private Team getTeamById(Integer id) {
		ResultSet result = executeSqlSelect("SELECT * FROM teams WHERE idteams = " + id);
		try {
			while(result.next()) {
				return new Team(
						result.getInt("idteams"),
						result.getString("name"),
						getGroupNameById(result.getInt("groupId"))
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private User getUserById(Integer id) {
		ResultSet result = executeSqlSelect("SELECT * FROM users WHERE idusers = " + id);
		try {
			while(result.next()) {
				return new User(
						result.getInt("idusers"),
						result.getBoolean("active"),
						result.getString("name"),
						result.getString("password")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}






	@Override
	public void registerForNotification(EuroBetDbFactory n) throws RemoteException {
		System.out.println("registerForNotification: " +n);
		clientList.add(n);
	}


	private void notifiyOnUserUpdated(User user) throws RemoteException {

		for(EuroBetDbFactory client : clientList) {
			client.onUserUpdated(user);
		}
	}

	private void notifiyOnUserAdded(User user) throws RemoteException {
		for(EuroBetDbFactory client : clientList) {
			client.onUserAdded(user);
		}
	}


	private void notifiyOnTeamUpdated(Team team) throws RemoteException {

		for(EuroBetDbFactory client : clientList) {
			client.onTeamUpdated(team);
		}
	}

	private void notifiyOnTeamAdded(Team team) throws RemoteException {
		for(EuroBetDbFactory client : clientList) {
			client.onTeamAdded(team);
		}
	}


	private void notifiyOnGameUpdated(Game game) throws RemoteException {

		for(EuroBetDbFactory client : clientList) {
			client.onGameUpdated(game);
		}
	}

	private void notifiyOnGameAdded(Game game) throws RemoteException {
		for(EuroBetDbFactory client : clientList) {
			client.onGameAdded(game);
		}
	}

	private void notifiyOnBetUpdated(Bet bet) throws RemoteException {

		for(EuroBetDbFactory client : clientList) {
			client.onBetUpdated(bet);
		}
	}

	private void notifiyOnBetAdded(Bet bet) throws RemoteException {
		for(EuroBetDbFactory client : clientList) {
			client.onBetAdded(bet);
		}
	}



	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://" + DB_URL + ":" + DB_PORT + "/" + DB_NAME, DB_USER, DB_PASSWORD);
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

	private Integer executeSqlUpdate(String sql) {

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







	public static void main(String[] args) {

		try {
			String hostPort = Util.getHostPortArg(args);
			EuroBetDb b = (EuroBetDb) new EuroBetDbServer();
			EuroBetDb buffSkel = (EuroBetDb)UnicastRemoteObject.exportObject(b, 0);
			LocateRegistry.createRegistry(Util.getPort(hostPort));

	  		Naming.rebind("rmi://" + hostPort + "/EuroBetDb", buffSkel);

	  		System.out.println("EuroBetDb Server running, waiting for connections ...");

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
