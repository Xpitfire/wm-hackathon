package swe4.gui.panels;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import swe4.gui.NumberTextField;
import swe4.gui.ToggleSwitch;
import swe4.gui.components.PanelComponent;
import swe4.gui.helper.GuiHelper;
import swe4.models.Game;
import swe4.models.Team;
import swe4.models.User;

public class ResultDetailPanel extends PanelComponent {

	
	private NumberTextField goalsTeamABox;
	private NumberTextField goalsTeamBBox;
	
	ArrayList<Team> teams = new ArrayList<Team>();
	private ComboBox<String> gameBox;

	public ResultDetailPanel() {
		super("Details");
		
		addHeaderButtonRight("result-save-button", "/save.png", event -> {
			System.out.println("result-save-button: "+ event.getSource());
		});
		
		createDetailFields();
	}
	
	private void createDetailFields() {
		
		VBox details = new VBox();
		details.setSpacing(50);
		details.setId("result-detail-container");
		
		
        

        
        // create demo teams
		List<Game> games = new ArrayList<>();
        Team t1 = new Team("Austria", 'G');
        Team t2 = new Team("Spain", 'G');
        Team t3 = new Team("Island", 'G');
        Team t4 = new Team("Portugal", 'A');
        
        // create demo games which are finished
        Game g1 = new Game(t1, t2);
        g1.setGameFinished(true);
        g1.setGoalsTeamA(2);
        g1.setGoalsTeamB(0);
        
        Game g2 = new Game(t2, t3);
        g2.setGameFinished(true);
        g2.setGoalsTeamA(4);
        g2.setGoalsTeamB(1);
        
        Game g3 = new Game(t3, t4);
        g3.setGameFinished(true);
        g3.setGoalsTeamA(0);
        g3.setGoalsTeamB(3);
        
        Game g4 = new Game(t1, t4);
        g4.setGameFinished(true);
        g4.setGoalsTeamA(2);
        g4.setGoalsTeamB(2);
        
        games.add(g1);
        games.add(g2);
        games.add(g3);
        games.add(g4);
		

        
        // game
        VBox game = new VBox();
 		game.setSpacing(3);
 		Label gameTitle = new Label("Game:");
 		ObservableList<String> optionsB = 
 			    FXCollections.observableArrayList(
 			    	g1.toString(),
 			    	g2.toString(),
 			    	g3.toString(),
 			    	g4.toString()
 			    );
 		gameBox = new ComboBox<String>(optionsB);
 		gameBox.setMinWidth(340);
 		game.getChildren().addAll(gameTitle, gameBox);
 		VBox.setMargin(game, new Insets(30, 30, 0, 30));
        
        
		// goals team a
        VBox goalsA = new VBox();
        goalsA.setSpacing(3);
 		Label goalsATitle = new Label("Goals Team A:");
 		goalsTeamABox = new NumberTextField ();
 		goalsA.getChildren().addAll(goalsATitle, goalsTeamABox);
 		VBox.setMargin(goalsA, new Insets(0, 30, 0, 30));
     	
 		
 		// goals team b
        VBox goalsB = new VBox();
        goalsB.setSpacing(3);
 		Label goalsBTitle = new Label("Goals Team B:");
 		goalsTeamBBox = new NumberTextField ();
 		goalsA.getChildren().addAll(goalsBTitle, goalsTeamBBox);
 		VBox.setMargin(goalsB, new Insets(0, 30, 0, 30));
		
		details.getChildren().addAll(
				game,goalsA, goalsB
		);
		
		addContent(details);
		
	}
	
	public void setDetails(Game game) {
		if(game == null) { return; }
		
		gameBox.setValue(game.toString());
		goalsTeamABox.setText(game.getGoalsTeamA().toString());
		goalsTeamBBox.setText(game.getGoalsTeamB().toString());
	}
	
	public void createNewDetail() {
		gameBox.setValue("");
		goalsTeamABox.setText("");
		goalsTeamBBox.setText("");
	}
	
	
}
