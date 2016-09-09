package swe4.rmi.client;


import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import swe4.models.Bet;
import swe4.models.Game;
import swe4.models.Team;
import swe4.models.User;
import swe4.rmi.server.EuroBetDb;
import swe4.util.Util;


public class EuroBetDbFactory implements Serializable, Remote  {
	

	private static final long serialVersionUID = 1944025441928560415L;
	private static EuroBetDbFactory instance = null;
	private static EuroBetDb db;
	
	private List<UserAddedListener> userAddedListeners = new ArrayList<>();
	private List<UserUpdatedListener> userUpdatedListeners = new ArrayList<>();
	private List<TeamAddedListener> teamAddedListeners = new ArrayList<>();
	private List<TeamUpdatedListener> teamUpdatedListeners = new ArrayList<>();
	private List<GameAddedListener> gamesAddedListeners = new ArrayList<>();
	private List<GameUpdatedListener> gamesUpdatedListeners = new ArrayList<>();
	private List<BetAddedListener> betsAddedListeners = new ArrayList<>();
	private List<BetUpdatedListener> betsUpdatedListeners = new ArrayList<>();
	
	
	private EuroBetDbFactory() throws MalformedURLException, RemoteException, NotBoundException {

		String hostPort = Util.getHostPortArg(null);
		
		db = (EuroBetDb) Naming.lookup("rmi://" + hostPort + "/EuroBetDb");
		
	}
	
	public static EuroBetDbFactory getInstance() {
		if(instance == null) {
	        
			try {
				instance = new EuroBetDbFactory();
				System.out.println("Register factory: " + instance);
				db.registerForNotification(instance);
			} catch (MalformedURLException | RemoteException | NotBoundException e) {
				System.out.println("Error: Unable to connect to server!");
			}
	    }
	    return instance;
	}
	
	public void removeAllUsers() {
		try {
			db.removeAllUsers();
		} catch (RemoteException e) {
			System.out.println("Error: Unable to remove users from server!");
		}
	}
	public List<User> getUsers() {
		try {
			return db.getUsers();
		} catch (RemoteException e) {
			System.out.println("Error: Unable to fetch users from server!");
			return null;
		}	
	}
	
	
	public void updateUser(User user) {
		try {
			db.updateUser(user);
			onUserUpdated(user); 
		} catch (RemoteException e) {
			System.out.println("Error: Unable to update user at server!");
		}	
	}
	
	
	public Integer addUser(User user) {
		try {
			Integer newId =  db.addUser(user);
			user.setId(newId);
			onUserAdded(user); 
			return user.getId();
		} catch (RemoteException e) {
			System.out.println("Error: Unable to add user at server!");
			return -1;
		}	
	}
	
	
	public void addUserAddedListener(UserAddedListener listener) {
		userAddedListeners.add(listener);	
    }
	
	public void addUserUpdatedListener(UserUpdatedListener listener) {
		userUpdatedListeners.add(listener);
    }

	
	public void onUserAdded(User user) throws RemoteException {
		for(UserAddedListener l : userAddedListeners) {
			l.onUserAdded(user);
		}		
	}

	
	public void onUserUpdated(User user) throws RemoteException {
		for(UserUpdatedListener l : userUpdatedListeners) {
			l.onUserUpdated(user);
		}
	}
	
	
	
	
	
	public void removeAllGroups() {
		try {
			db.removeAllGroups();
		} catch (RemoteException e) {
			System.out.println("Error: Unable to remove groups from server!");
		}
	}
	
	
	public Integer addGroup(Character group) {
		
		try {
			return db.addGroup(group);
		} catch (RemoteException e) {
			System.out.println("Error: Unable to add group to server!");
			return null;
		}	
	}
	
	public List<String> getGroups() {
		try {
			return db.getGroups();
		} catch (RemoteException e) {
			System.out.println("Error: Unable to fetch groups from server!");
			return null;
		}	
	}
	
	
	
	public void removeAllTeams() {
		try {
			db.removeAllTeams();
		} catch (RemoteException e) {
			System.out.println("Error: Unable to remove teams from server!");
		}
	}
	
	public List<Team> getTeams() {
		try {
			return db.getTeams();
		} catch (RemoteException e) {
			System.out.println("Error: Unable to fetch teams from server!");
			return null;
		}	
	}
	
	public void updateTeam(Team team) {
		try {
			db.updateTeam(team);
			onTeamUpdated(team); 
		} catch (RemoteException e) {
			System.out.println("Error: Unable to update team at server!");
		}	
	}
	
	public Integer addTeam(Team team) {
		try {
			Integer newId =  db.addTeam(team);
			team.setId(newId);
			onTeamAdded(team); 
			return team.getId();
		} catch (RemoteException e) {
			System.out.println("Error: Unable to add team at server!");
			return -1;
		}	
	}
	
	
	public void onTeamAdded(Team team) throws RemoteException {
		for(TeamAddedListener l : teamAddedListeners) {
			l.onTeamAdded(team);
		}		
	}

	
	public void onTeamUpdated(Team team) throws RemoteException {
		for(TeamUpdatedListener l : teamUpdatedListeners) {
			l.onTeamUpdated(team);
		}
	}
	
	
	public void addTeamAddedListener(TeamAddedListener listener) {
		teamAddedListeners.add(listener);	
    }
	
	public void addTeamUpdatedListener(TeamUpdatedListener listener) {
		
		teamUpdatedListeners.add(listener);
    }
	
	
	
	
	
	
	public void removeAllGames() {
		try {
			db.removeAllGames();
		} catch (RemoteException e) {
			System.out.println("Error: Unable to remove games from server!");
		}
	}
	
	public List<Game> getGames() {
		try {
			return db.getGames();
		} catch (RemoteException e) {
			System.out.println("Error: Unable to fetch games from server!");
			return null;
		}	
	}
	
	public void updateGame(Game game) {
		try {
			db.updateGame(game);
			onGameUpdated(game); 
		} catch (RemoteException e) {
			System.out.println("Error: Unable to update team at server!");
		}	
	}
	
	public Integer addGame(Game game) {
		try {
			Integer newId =  db.addGame(game);
			game.setId(newId);
			onGameAdded(game); 
			return game.getId();
		} catch (RemoteException e) {
			System.out.println("Error: Unable to add team at server!");
			return -1;
		}	
	}
	
	
	public void onGameAdded(Game game) throws RemoteException {
		for(GameAddedListener l : gamesAddedListeners) {
			l.onGameAdded(game);
		}		
	}

	
	public void onGameUpdated(Game game) throws RemoteException {
		for(GameUpdatedListener l : gamesUpdatedListeners) {
			l.onGameUpdated(game);
		}
	}
	
	public void addGamesAddedListener(GameAddedListener listener) {
		gamesAddedListeners.add(listener);	
    }
	
	public void addGamesUpdatedListener(GameUpdatedListener listener) {
		
		gamesUpdatedListeners.add(listener);
    }
	

	
	
	public void removeAllBets() {
		try {
			db.removeAllBets();
		} catch (RemoteException e) {
			System.out.println("Error: Unable to remove bets from server!");
		}
	}
	
	public List<Bet> getBets() {
		try {
			return db.getBets();
		} catch (RemoteException e) {
			System.out.println("Error: Unable to fetch bets from server!");
			return null;
		}	
	}
	
	public void updateBet(Bet bet) {
		try {
			db.updateBet(bet);
		} catch (RemoteException e) {
			System.out.println("Error: Unable to update bet at server!");
		}	
	}
	
	public Integer addBet(Bet bet) {
		try {
			Integer newId =  db.addBet(bet);
			bet.setId(newId);
			return bet.getId();
		} catch (RemoteException e) {
			System.out.println("Error: Unable to add bet at server!");
			return -1;
		}	
	}
	
	
	public void onBetAdded(Bet bet) throws RemoteException {
		for(BetAddedListener l : betsAddedListeners) {
			l.onBetAdded(bet);
		}		
	}

	
	public void onBetUpdated(Bet bet) throws RemoteException {
		for(BetUpdatedListener l : betsUpdatedListeners) {
			l.onBetUpdated(bet);
		}
	}
	
	public void addBetsAddedListener(BetAddedListener listener) {
		betsAddedListeners.add(listener);
    }
	
	public void addBetsUpdatedListener(BetUpdatedListener listener) {
		
		betsUpdatedListeners.add(listener);
    }
	

}
