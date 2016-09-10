package wm.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import wm.lib.WmDb;
import wm.lib.WmRmi;
import wm.lib.model.Tip;
import wm.lib.model.User;
import wm.lib.model.WmState;
import wm.server.db.WmDbMySqlImpl;

public class WmClientTest {
	String host_port = "localhost:18901";
	
	@Test
	public void testAuthenticateUser() {
	    try {
			WmRmi wmProxy = (WmRmi)Naming.lookup("rmi://" + host_port + "/WmRmi");
			assertFalse(wmProxy.authenticateUser("matrix", "asd"));
			assertTrue(wmProxy.authenticateUser("matrix", "asdf"));
	    } catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPlaceTip() {
	    try {
			WmRmi wmProxy = (WmRmi)Naming.lookup("rmi://" + host_port + "/WmRmi");
			int id = wmProxy.createGame("AUT", "ISR", "GER", new Date(System.currentTimeMillis()), WmState.FINAL);
			Tip tip = new Tip("matrix", id, 3, 1, "AUT", "ISR", "GER", "AUT", "POL", "GER");
			int tipId = wmProxy.addTip(tip);
			assertTrue(wmProxy.evaluateTip(tipId) == -1);
			wmProxy.setFinalResult(id, 1, 2);
			System.out.println(wmProxy.evaluateTip(tipId));
	    } catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}

