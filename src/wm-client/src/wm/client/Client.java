package wm.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import wm.lib.WmRmi;
import wm.lib.model.User;

public class Client {
	
	  public static void main(String[] args) {

	    String host_port = "localhost:18901";
	    
	    try {
			WmRmi wmProxy = (WmRmi)Naming.lookup("rmi://" + host_port + "/WmRmi");
			List<User> users = wmProxy.getUsers();
			System.out.println("------------------------------");
			
			for (int i = 0; i < users.size(); i++) {
				System.out.println("User: " + users.get(i).getUsername());
			}
	    } catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	  }

}
