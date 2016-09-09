package wm.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import wm.lib.WmDb;
import wm.lib.WmRmi;
import wm.server.db.WmDbMySqlImpl;
import wm.server.logic.WmLogicImpl;

public class Server {

	public static void main(String[] args) {
		String host_port = "localhost";
		int port = 18901;
		try {
			// generate registry
			LocateRegistry.createRegistry(18901);

			// generate service object and export service object
			WmDb wmdb = new WmDbMySqlImpl();
			WmRmi serviceStub = new WmLogicImpl(wmdb);
			UnicastRemoteObject.exportObject(serviceStub, 0);

			// register service object
			Naming.rebind("rmi://" + host_port + ":" + port +  "/WmRmi", serviceStub);

			System.out.println("WmRmi service started");
		} catch (RemoteException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
