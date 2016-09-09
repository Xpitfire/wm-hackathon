package swe4.gui;

import java.util.logging.LogManager;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import swe4.gui.components.RouteComponent;
import swe4.gui.components.SidebarComponent;
import swe4.gui.pages.GamesPage;
import swe4.gui.pages.ResultsPage;
import swe4.gui.pages.TeamsPage;
import swe4.gui.pages.UsersPage;

public class EuroBetUI extends Application {
	
	private static final Background ROOT_BACKGROUND = new Background
			(new BackgroundFill(Color.WHITE, null, null));

	private static final String URL_USERS = "/users";
	private static final String URL_TEAMS = "/teams";
	private static final String URL_GAMES = "/games";
	private static final String URL_RESULTS = "/results";
	
	
	
	public static void main(String[] args) {
		LogManager.getLogManager().reset();
		launch(args);
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {

		
		RouteComponent pages = new RouteComponent();
		pages.setPadding(new Insets(0,0,0,0));
		
		// add all pages to routing
		pages.addPage(URL_USERS, new UsersPage());
		pages.addPage(URL_TEAMS, new TeamsPage());
		pages.addPage(URL_GAMES, new GamesPage());
		pages.addPage(URL_RESULTS, new ResultsPage());
		
		// show default page
		pages.showPage(URL_USERS);
		
		SidebarComponent sideBar = new SidebarComponent();
	
		
		sideBar.addNavigationButton("Users", "/user.png", event -> {
			pages.showPage(URL_USERS);
		});
		
		sideBar.addNavigationButton("Teams", "/teams.png", event -> {
			pages.showPage(URL_TEAMS);
		});
		
		
		sideBar.addNavigationButton("Games", "/games.png", event -> {
			pages.showPage(URL_GAMES);
		});
		
		
		sideBar.addNavigationButton("Results","/results.png", event -> {
			pages.showPage(URL_RESULTS);
		});
		
		
		BorderPane rootPane = new BorderPane();
		rootPane.setLeft(sideBar);
		rootPane.setCenter(pages);
		rootPane.setBackground(ROOT_BACKGROUND); // main background

		Scene scene = new Scene(rootPane, 500, 500);
		scene.getStylesheets().add(
				getClass().getResource("/euro-bet-ui.css").toExternalForm());

		primaryStage.setScene(scene);


		primaryStage.setMinWidth(1070);
		primaryStage.setMaxWidth(1070);
		primaryStage.setMinHeight(600);
		primaryStage.setMaxHeight(600);
		
		primaryStage.setTitle("EURO-Bet-UI");
		primaryStage.show();
	}
}
