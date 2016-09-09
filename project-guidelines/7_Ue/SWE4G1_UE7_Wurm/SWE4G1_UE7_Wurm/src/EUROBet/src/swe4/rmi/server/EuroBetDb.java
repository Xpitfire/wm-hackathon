package swe4.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import swe4.models.Bet;
import swe4.models.Game;
import swe4.models.Team;
import swe4.models.User;
import swe4.rmi.client.EuroBetDbFactory;

public interface EuroBetDb extends Remote {
	
	/**
	 * removes all users from database
	 * @throws RemoteException
	 */
	void removeAllUsers() throws RemoteException;
	
	/**
	 * get all users from database
	 * @return
	 * @throws RemoteException
	 */
	List<User> getUsers() throws RemoteException;
	
	
	/**
	 * updates an existing user in database
	 * @param user
	 * @throws RemoteException
	 */
	void updateUser(User user) throws RemoteException;
	
	
	/**
	 * creates a new user in database
	 * @param user
	 * @return id from created user
	 * @throws RemoteException
	 */
	Integer addUser(User user) throws RemoteException;
	
	
	/**
	 * removes all groups from database
	 * @throws RemoteException
	 */
	void removeAllGroups() throws RemoteException;
	
	
	/**
	 * creates a new group in database
	 * @param group
	 * @return group name
	 * @throws RemoteException
	 */
	Integer addGroup(Character group) throws RemoteException;
	
	
	/**
	 * get all groups from database
	 * @return
	 * @throws RemoteException
	 */
	List<String> getGroups() throws RemoteException;
	
	
	
	/**
	 * removes all teams from database
	 * @throws RemoteException
	 */
	void removeAllTeams() throws RemoteException;
	
	/**
	 * get all teams from database
	 * @return
	 * @throws RemoteException
	 */
	List<Team> getTeams() throws RemoteException;
	
	
	
	/**
	 * updates an existing team in database
	 * @param user
	 * @throws RemoteException
	 */
	void updateTeam(Team team) throws RemoteException;
	
	
	/**
	 * creates a new team in database
	 * @param team
	 * @return id from created team
	 * @throws RemoteException
	 */
	Integer addTeam(Team team) throws RemoteException;
	
	
	/**
	 * removes all games from database
	 * @throws RemoteException
	 */
	void removeAllGames() throws RemoteException;
	
	/**
	 * get all games from database
	 * @return
	 * @throws RemoteException
	 */
	List<Game> getGames() throws RemoteException;
	
	
	/**
	 * updates an existing team in database
	 * @param user
	 * @throws RemoteException
	 */
	void updateGame(Game game) throws RemoteException;
	
	
	/**
	 * creates a new game in database
	 * @param game
	 * @return id from created game
	 * @throws RemoteException
	 */
	Integer addGame(Game game) throws RemoteException;
	
	
	
	/**
	 * removes all bets from database
	 * @throws RemoteException
	 */
	void removeAllBets() throws RemoteException;
	
	/**
	 * get all games from database
	 * @return
	 * @throws RemoteException
	 */
	List<Bet> getBets() throws RemoteException;
	
	
	/**
	 * creates a new bet in database
	 * @param bet
	 * @return id from created bet
	 * @throws RemoteException
	 */
	Integer addBet(Bet bet) throws RemoteException;
	
	
	/**
	 * updates an existing bet in database
	 * @param bet
	 * @throws RemoteException
	 */
	void updateBet(Bet bet) throws RemoteException;
	
	
	
	// called by clients to register for new server callbacks
	public void registerForNotification(EuroBetDbFactory n) throws RemoteException;
}