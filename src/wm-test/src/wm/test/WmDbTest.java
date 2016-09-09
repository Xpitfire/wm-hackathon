package wm.test;

import org.junit.Test;

import wm.lib.WmDb;
import wm.lib.model.User;
import wm.server.db.WmDbMySqlImpl;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

public class WmDbTest {
	
	private WmDb wmdb = new WmDbMySqlImpl();
	private User user = new User("demo", "demo", "Viktoria", "Neumayer");
	
	@Test
	public void testAddUser() {
		String username = TestHelper.getRandomString(5);
		User user = new User(username, "demo", "Viktoria", "Neumayer");
		wmdb.add(user);
		// if no exception happened, everything is fine
		assertTrue(true);
	}
	
	@Test
	public void testGetUsers() {
		List<User> users = wmdb.getUsers();
		assertTrue(users.size() > 0);
	}
	
	@Test
	public void testUpdateUserBlock() {
		List<User> users = wmdb.getUsers();
		wmdb.block(users.get(0));
		assertFalse(wmdb.getUser(users.get(0).getUsername()).isActive());
	}

}
