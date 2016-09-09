package wm.lib;

import java.util.Date;
import java.util.List;

import wm.lib.model.*;

public interface WmLogic {
	void add(User user) throws Exception;
	void block(User user) throws Exception;
	List<User> getUsers() throws Exception;
	
	void add(Team team) throws Exception;
	void delete(Team team) throws Exception;
	void update(Team team) throws Exception;
	List<Team> getTeams() throws Exception;
	
	/**
	 * Can add game without end/final result.
	 * @param game
	 */
	Game createGame(Team team1, Team team2, String place, Date date, WmState wmState) throws Exception;
	void delete(Game game) throws Exception;
	/**
	 * Is not allowed to update the game score.
	 * @param game
	 */
	void update(Game game) throws Exception;
	void setFinalResult(Game game) throws Exception;
	List<Game> getGames() throws Exception;

	/**
	 * Weights the tip depending on the
	 * score.
	 * @param tip
	 */
	void add(Tip tip) throws Exception;
	List<Tip> getTips() throws Exception;
}
