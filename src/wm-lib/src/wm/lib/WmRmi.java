package wm.lib;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import wm.lib.model.Game;
import wm.lib.model.Team;
import wm.lib.model.Tip;
import wm.lib.model.User;

public interface WmRmi extends WmLogic, Remote {
	void add(User user) throws RemoteException;
	void block(User user) throws RemoteException;
	List<User> getUsers() throws RemoteException;
	void add(Team team) throws RemoteException;
	void delete(Team team) throws RemoteException;
	void update(Team team) throws RemoteException;
	List<Team> getTeams() throws RemoteException;
	Game createGame(Team team1, Team team2, String place, Date date, WmState wmState) throws RemoteException;
	void delete(Game game) throws RemoteException;
	void update(Game game) throws RemoteException;
	void setFinalResult(Game game) throws RemoteException;
	List<Game> getGames() throws RemoteException;
	void add(Tip tip) throws RemoteException;
	List<Tip> getTips() throws RemoteException;
}
