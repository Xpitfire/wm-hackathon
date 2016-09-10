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
import wm.lib.model.Game;
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
			Tip tip = new Tip("matrix", 1002, 1, 3, "ISR", "POL", "AUT", "FIN", "BHS", "CAN");
			int tipId = wmProxy.addTip(tip);
			wmProxy.setFinalResult(1002, 3, 1);
			assertTrue(wmProxy.evaluateTip(tipId) == 0);
			wmProxy.setFinalResult(1002, 1, 2);
			assertTrue(wmProxy.evaluateTip(tipId) == 1);
			wmProxy.setFinalResult(1002, 0, 2);
			assertTrue(wmProxy.evaluateTip(tipId) == 2);
			wmProxy.setFinalResult(1002, 1, 3);
			assertTrue(wmProxy.evaluateTip(tipId) == 4);
			assertTrue(wmProxy.evaluateBonusQuestions(tipId) == 6);
	    } catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}

