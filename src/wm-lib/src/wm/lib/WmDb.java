package wm.lib;

import java.util.List;

import wm.lib.model.*;

public interface WmDb {
	void add(User user);
	void block(User user);
	List<User> getUsers();
	
	void add(Team team);
	void delete(Team team);
	void update(Team team);
	List<Team> getTeams();
	
	void add(Game game);
	void delete(Game game);
	void update(Game game);
	List<Game> getGames();
	
	void add(Tip tip);
	List<Tip> getTips();
}
