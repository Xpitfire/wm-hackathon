package swe4.test;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Test;

import swe4.models.Game;
import swe4.models.Team;
import swe4.models.User;
import swe4.rmi.client.EuroBetDbFactory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EuroDbServerTest {
	
	
	EuroBetDbFactory server;
	private Integer zero = 0;
	
	
	@Test
	public void t00_RemoveAllGames() {
		Integer current;
		EuroBetDbFactory.getInstance().removeAllGames();
		current = EuroBetDbFactory.getInstance().getGames().size();
		assertEquals(zero, current);
	}
	
	@Test
	public void t01_RemoveAllTeams() {
		Integer current;
		EuroBetDbFactory.getInstance().removeAllTeams();
		current = EuroBetDbFactory.getInstance().getTeams().size();
		assertEquals(zero, current);
		
	}
	
	@Test
	public void t02_RemoveAllGroups() {
		Integer current;
		EuroBetDbFactory.getInstance().removeAllGroups();
		current = EuroBetDbFactory.getInstance().getGroups().size();
		assertEquals(zero, current);
	}	
	
	@Test
	public void t03_RemoveAllUsers() {
		Integer current;
		EuroBetDbFactory.getInstance().removeAllUsers();
		current = EuroBetDbFactory.getInstance().getUsers().size();
		assertEquals(zero, current);
	}
	
	
	@Test
	public void t04_AddUsers() {
		User user = new User();
		
		user.setName("Robert Wurm");
		user.setActive(false);
		user.setPassword("1234");
		EuroBetDbFactory.getInstance().addUser(user);
		
		user.setName("Hansi Hinterseer");
		user.setActive(true);
		user.setPassword("123456");
		EuroBetDbFactory.getInstance().addUser(user);
		
		
		user.setName("Frau Holle");
		user.setActive(true);
		user.setPassword("xxx");
		EuroBetDbFactory.getInstance().addUser(user);
		
		
		
		user.setName("Asterix");
		user.setActive(true);
		user.setPassword("xx56x");
		EuroBetDbFactory.getInstance().addUser(user);
		
		
		
		List<User> allUsers = EuroBetDbFactory.getInstance().getUsers();
		assertNotNull(allUsers);
		assertEquals(4, allUsers.size());
		
		Boolean robertExists = false;
		Boolean asterixExists = false;
		for(User u : allUsers) {
			if(u.getName().equals("Robert Wurm")) {
				assertEquals(u.getPassword(), "1234");
				assertEquals(u.getActive(), false);
				robertExists = true;
			}
			
			if(u.getName().equals("Asterix")) {
				assertEquals(u.getPassword(), "xx56x");
				assertEquals(u.getActive(), true);
				asterixExists = true;
			}
			
			if(u.getName().equals("Dummy !!!")) {
				fail("username shouldnt exists!");
			}
		}
		assertEquals(true, robertExists);
		assertEquals(true, asterixExists);
	}
	
	
	@Test
	public void t05_AddGroups() {
		
		EuroBetDbFactory.getInstance().addGroup('A');
		EuroBetDbFactory.getInstance().addGroup('B');
		EuroBetDbFactory.getInstance().addGroup('C');
		EuroBetDbFactory.getInstance().addGroup('D');
		EuroBetDbFactory.getInstance().addGroup('E');
		EuroBetDbFactory.getInstance().addGroup('F');
		
		List<String> allGroups = EuroBetDbFactory.getInstance().getGroups();
		
		assertNotNull(allGroups);
		assertEquals(6, allGroups.size());
		
		Boolean existsA = false;
		Boolean existsD = false;
		for(String g : allGroups) {
			if(g.equals("A")) {
				existsA = true;
			}
			
			if(g.equals("D")) {
				existsD = true;
			}
			if(g.equals("Z")) {
				fail("group shouldnt exists!");
			}
		}
		assertEquals(true, existsA);
		assertEquals(true, existsD);
	}
	
	
	@Test
	public void t06_AddTeams() {
		Team team = new Team();
		Integer id;
		Integer unableToCreate = -1;
		
		team.setName("France");
		team.setGroup("Z");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertEquals(id, unableToCreate);
		team.setGroup("A");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		
		team.setName("Switzerland");
		team.setGroup("A");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		
		team.setName("Romania");
		team.setGroup("A");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		
		team.setName("Albania");
		team.setGroup("A");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		
		team.setName("Wales");
		team.setGroup("B");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		team.setName("Slovakia");
		team.setGroup("B");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		team.setName("England");
		team.setGroup("B");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		team.setName("Russia");
		team.setGroup("B");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		team.setName("Germany");
		team.setGroup("C");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		team.setName("Poland");
		team.setGroup("C");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		team.setName("Northern Ireland");
		team.setGroup("C");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		team.setName("Ukraine");
		team.setGroup("C");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		team.setName("Croatia");
		team.setGroup("D");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		team.setName("Spain");
		team.setGroup("D");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		team.setName("Czech Republic");
		team.setGroup("D");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		team.setName("Turkey");
		team.setGroup("D");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		
		team.setName("Italy");
		team.setGroup("E");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		team.setName("Irland");
		team.setGroup("E");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		team.setName("Sweden");
		team.setGroup("E");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		team.setName("Belgium");
		team.setGroup("E");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		team.setName("Hungary");
		team.setGroup("F");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		team.setName("Iceland");
		team.setGroup("F");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		team.setName("Portugal");
		team.setGroup("F");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		team.setName("Austria");
		team.setGroup("F");
		id = EuroBetDbFactory.getInstance().addTeam(team);
		assertNotEquals(id, unableToCreate);
		
		List<Team> allTeams = EuroBetDbFactory.getInstance().getTeams();
		assertNotNull(allTeams);
		assertEquals(24, allTeams.size());
		
		Boolean austriaExists = false;
		Boolean walesExists = false;
		for(Team t : allTeams) {
			if(t.getName().equals("Austria")) {
				austriaExists = true;
			}
			
			if(t.getName().equals("Wales")) {
				walesExists = true;
			}
			
			if(t.getName().equals("Lichtenstein")) {
				fail("team shouldnt exists!");
			}
		}
		assertEquals(true, austriaExists);
		assertEquals(true, walesExists);
	}
	
	
	@Test
	public void t07_AddGames() {
		Game game = new Game();
		Team austria = null;
		Team iceland = null;
		Team portugal = null;
		Team hungary = null;
		
		List<Team> allTeams = EuroBetDbFactory.getInstance().getTeams();
		
		for(Team t : allTeams) {
			if(t.getName().equals("Austria")) { austria = t; }
			if(t.getName().equals("Iceland")) { iceland = t; }
			if(t.getName().equals("Hungary")) { hungary = t; }
			if(t.getName().equals("Portugal")) { portugal = t; }
		}
		
		assertNotNull(austria);
		assertNotNull(iceland);
		assertNotNull(portugal);
		assertNotNull(hungary);
		
		
		
		game.setTeamA(austria);
		game.setTeamB(hungary);
		game.setGameFinished(true);
		game.setGoalsTeamA(0);
		game.setGoalsTeamB(2);
		EuroBetDbFactory.getInstance().addGame(game);
		
		
		game.setTeamA(portugal);
		game.setTeamB(iceland);
		game.setGameFinished(true);
		game.setGoalsTeamA(1);
		game.setGoalsTeamB(1);
		EuroBetDbFactory.getInstance().addGame(game);
		
		
		game.setTeamA(portugal);
		game.setTeamB(austria);
		game.setGameFinished(false);
		EuroBetDbFactory.getInstance().addGame(game);
		
		
		game.setTeamA(hungary);
		game.setTeamB(portugal);
		EuroBetDbFactory.getInstance().addGame(game);
		
		game.setTeamA(iceland);
		game.setTeamB(hungary);
		EuroBetDbFactory.getInstance().addGame(game);
		
		game.setTeamA(austria);
		game.setTeamB(iceland);
		EuroBetDbFactory.getInstance().addGame(game);
	}
	
	
	@Test
	public void t08_UpdateUser() {
		List<User> allUsers = EuroBetDbFactory.getInstance().getUsers();
		User robert = null;
		User hansi = null;
		User hansy = null;
		for(User u : allUsers) {
			if(u.getName().equals("Robert Wurm")) { robert = u; }
			if(u.getName().equals("Hansi Hinterseer")) { hansi = u; }
			if(u.getName().equals("Hansy Hinterseer")) { hansy = u; }
		}
		assertNotNull(robert);
		assertNotNull(hansi);
		assertNull(hansy);
		
		
		robert.setName("Updated Robert");
		EuroBetDbFactory.getInstance().updateUser(robert);
		
		
		allUsers = EuroBetDbFactory.getInstance().getUsers();
		robert = null;
		hansi = null;
		for(User u : allUsers) {
			if(u.getName().equals("Updated Robert")) { robert = u; }
			if(u.getName().equals("Hansi Hinterseer")) { hansi = u; }
			if(u.getName().equals("Hansy Hinterseer")) { hansy = u; }
		}
		assertNull(hansy);
		assertNotNull(robert);
		assertNotNull(hansi);
		
		assertEquals(robert.getName(), "Updated Robert");
		assertEquals(hansi.getName(), "Hansi Hinterseer");
		
		
		robert.setName("Robert Wurm");
		EuroBetDbFactory.getInstance().updateUser(robert);
	}
	
	
	@Test
	public void t09_UpdateGame() {
		Game game = null;
		List<Game> allGames;
		
		allGames = EuroBetDbFactory.getInstance().getGames();
		
		for(Game g : allGames) {
			if(g.getTeamA().getName().equals("Austria") && 
				g.getTeamB().getName().equals("Iceland")) {
				game = g;
			}
		}
		
		assertNotNull(game);
	
		Integer newGoalsTeamA = 10;
		Integer newGoalsTeamB = 0;
		game.setGameFinished(true);
		game.setGoalsTeamA(newGoalsTeamA);
		game.setGoalsTeamB(newGoalsTeamB);
		
		EuroBetDbFactory.getInstance().updateGame(game);
		
		game = null;
		allGames = EuroBetDbFactory.getInstance().getGames();
		
		for(Game g : allGames) {
			if(g.getTeamA().getName().equals("Austria") && 
				g.getTeamB().getName().equals("Iceland")) {
				game = g;
			}
		}
		
		assertNotNull(game);
		
		assertEquals(game.getGoalsTeamA(), newGoalsTeamA);
		assertNotEquals(game.getGoalsTeamB(), newGoalsTeamA);
		
		game.setGoalsTeamA(0);
		game.setGoalsTeamB(2);
		
		EuroBetDbFactory.getInstance().updateGame(game);
	}
	
	@Test
	public void t10_UpdateTeam() {
		Team austria = null;
		List<Team> allTeams;

		
		allTeams = EuroBetDbFactory.getInstance().getTeams();
		for(Team t:allTeams) {
			if(t.getName().equals("Austria")) { austria = t; };
		}
		assertNotNull(austria);
		
		austria.setName("Australia");
		EuroBetDbFactory.getInstance().updateTeam(austria);
		
		austria = null;
		allTeams = EuroBetDbFactory.getInstance().getTeams();
		for(Team t:allTeams) {
			if(t.getName().equals("Australia")) { austria = t; };
		}
		assertNotNull(austria);
		
		assertEquals(austria.getName(), "Australia");
		
		
		
		austria.setName("Austria");
		EuroBetDbFactory.getInstance().updateTeam(austria);
		
		austria = null;
		allTeams = EuroBetDbFactory.getInstance().getTeams();
		for(Team t:allTeams) {
			if(t.getName().equals("Austria")) { austria = t; };
		}
		assertNotNull(austria);
		
		assertEquals(austria.getName(), "Austria");
	}
}
