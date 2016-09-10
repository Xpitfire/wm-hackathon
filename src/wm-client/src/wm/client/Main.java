package wm.client;
	
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import wm.lib.WmRmi;
import wm.lib.model.User;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Client.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String host_port = "localhost:18901";
		try {
			WmRmi wmProxy = (WmRmi) Naming.lookup("rmi://" + host_port + "/WmRmi");
			List<User> users = wmProxy.getUsers();
			System.out.println("------------------------------");

			for (int i = 0; i < users.size(); i++) {
				System.out.println("User: " + users.get(i).getUsername());
			}
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		
		launch(args);
	}
}
