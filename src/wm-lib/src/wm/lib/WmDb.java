package wm.lib;

import java.util.List;

import wm.lib.model.*;

public interface WmDb {
	void add(User user);
	void block(User user);
	User getUser(String username);
	List<User> getUsers();
	
	void add(Team team);
	void delete(Team team);
	void update(Team team);
	Team getTeam(String countryId);
	List<Team> getTeams();
	
	int add(Game game);
	void delete(Game game);
	void update(Game game);
	Game getGame(int id);
	List<Game> getGames();
	
	int add(Tip tip);
	Tip getTip(int id);
	List<Tip> getTips();
}
