package wm.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import wm.lib.WmDb;
import wm.lib.model.Game;
import wm.lib.model.Team;
import wm.lib.model.Tip;
import wm.lib.model.User;

public class WmDbMySqlImpl implements WmDb {

	private static final String DB_URL = "127.0.0.1";
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "EuroBetDb";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";

	public WmDbMySqlImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void block(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		ResultSet result = executeSqlSelect("SELECT * FROM users");
		try {
			while (result.next()) {
				User user = new User(
						result.getString("username"), 
						result.getString("firstName"),
						result.getString("lastName"));
				user.setPasswordHash("passwordHash");
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
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
	public void add(Game game) {
		// TODO Auto-generated method stub

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
		return DriverManager.getConnection("jdbc:mysql://" + DB_URL + ":" + DB_PORT + "/" + DB_NAME, DB_USER,
				DB_PASSWORD);
	}

}
