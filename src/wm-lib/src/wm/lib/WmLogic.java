package wm.lib;

import java.util.Date;
import java.util.List;

import wm.lib.model.*;

public interface WmLogic {
	void add(User user);
	void block(User user);
	List<User> getUsers();
	
	void add(Team team);
	void delete(Team team);
	void update(Team team);
	List<Team> getTeams();
	
	/**
	 * Can add game without end/final result.
	 * @param game
	 */
	Game createGame(Team team1, Team team2, String place, Date date, WmState wmState);
	void delete(Game game);
	/**
	 * Is not allowed to update the game score.
	 * @param game
	 */
	void update(Game game);
	void setFinalResult(Game game);
	List<Game> getGames();

	/**
	 * Weights the tip depending on the
	 * score.
	 * @param tip
	 */
	void add(Tip tip);
	List<Tip> getTips();
}
