package wm.test;

import org.junit.Test;

import wm.lib.WmDb;
import wm.lib.model.User;
import wm.server.db.WmDbMySqlImpl;

import static org.junit.Assert.*;

public class WmDbTest {
	
	private WmDb wmdb = new WmDbMySqlImpl();
	
	@Test
	public void testUser() {
		User user = new User("demo", "demo", "Viktoria", "Neumayer");
		wmdb.add(user);
		// if no exception happened, everything is fine
		assertTrue(true);
	}

}
