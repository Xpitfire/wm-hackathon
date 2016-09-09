package wm.lib;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import wm.lib.model.Game;
import wm.lib.model.Team;
import wm.lib.model.Tip;
import wm.lib.model.User;
import wm.lib.model.WmState;

public interface WmRmi extends Remote {
	/**
	 * @param username
	 * @param passwordHash
	 * @param firstName
	 * @param lastName
	 * @return false if user already exists or one of the inputs is invalid
	 * @throws RemoteException
	 */
	boolean createUser(String username, String passwordHash, String firstName, String lastName) throws RemoteException;
	/**
	 * @param username
	 * @return false if user does not exist or is already blocked
	 * @throws RemoteException
	 */
	boolean blockUser(String username) throws RemoteException;
	/**
	 * @return list of all users
	 * @throws RemoteException
	 */
	List<User> getUsers() throws RemoteException;
	/**
	 * @param username
	 * @param passwordHash
	 * @return false if user does not exist or wrong password entered
	 * @throws RemoteException
	 */
	boolean authenticateUser(String username, String passwordHash) throws RemoteException;
	
	/**
	 * @param country
	 * @param name
	 * @return false if team already exists, name is empty or country does not have 3 characters
	 * @throws RemoteException
	 */
	boolean createTeam(String country, String name) throws RemoteException;
	/**
	 * @param country
	 * @return false if team does not exist
	 * @throws RemoteException
	 */
	boolean deleteTeam(String country) throws RemoteException;
	/**
	 * @param country
	 * @param name
	 * @return false if team does not exist or name is invalid
	 * @throws RemoteException
	 */
	boolean updateTeam(String country, String name) throws RemoteException;
	/**
	 * @return list of all teams
	 * @throws RemoteException
	 */
	List<Team> getTeams() throws RemoteException;
	
	/**
	 * Can add game without end/final result.
	 * @param team1
	 * @param team2
	 * @param place
	 * @param date
	 * @param wmState
	 * @return game id / -1 if one of the teams does not exist ore one of the inputs is invalid
	 * @throws RemoteException
	 */
	int createGame(String team1, String team2, String place, Date date, WmState wmState) throws RemoteException;
	/**
	 * @param id
	 * @return false if game does not exist
	 * @throws RemoteException
	 */
	boolean deleteGame(int id) throws RemoteException;
	/**
	 * @param id
	 * @param team1
	 * @param team2
	 * @param place
	 * @param date
	 * @param wmState
	 * @return false if one of the teams does not exist ore one of the inputs is invalid
	 * @throws RemoteException
	 */
	boolean updateGame(int id, String team1, String team2, String place, Date date, WmState wmState) throws RemoteException;
	/**
	 * @param id
	 * @param goal1
	 * @param goal2
	 * @return false if game does not exist or one of the inputs is invalid
	 * @throws RemoteException
	 */
	boolean setFinalResult(int id, int goal1, int goal2) throws RemoteException;
	/**
	 * 
	 * @return list of all games
	 * @throws RemoteException
	 */
	List<Game> getGames() throws RemoteException;
	
	/**
	 * @param tip
	 * @return tip id / -1 if game already finished or does not exist
	 * @throws RemoteException
	 */
	int addTip(Tip tip) throws RemoteException;
	/**
	 * @return list of all tips
	 * @throws RemoteException
	 */
	List<Tip> getTips() throws RemoteException;
	/**
	 * @param id
	 * @return total points reached at bet / -1 if tip does not exist or game not finished yet
	 * @throws RemoteException
	 */
	int evaluateTip(int id) throws RemoteException;
	
	/**
	 * @param id
	 * @return total points reached at bonus questions / -1 if tip does not exist or WM not finished yet
	 * @throws RemoteException
	 */
	int evaluateBonusQuestions(int id) throws RemoteException;
	
}
