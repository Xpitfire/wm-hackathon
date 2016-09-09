package swe4.gui.panels;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import swe4.gui.NumberTextField;
import swe4.gui.components.PanelComponent;
import swe4.models.Game;
import swe4.rmi.client.EuroBetDbFactory;

public class ResultDetailPanel extends PanelComponent {

	
	private NumberTextField goalsTeamABox;
	private NumberTextField goalsTeamBBox;
	private ComboBox<String> gameBox;
	private Game editedGame = new Game();
	private List<Game> allGames;

	public ResultDetailPanel() {
		super("Details");
		
		addHeaderButtonRight("result-save-button", "/save.png", null, event -> {
			// save or update game
			
			Integer gameSelectionIndex =  gameBox.getSelectionModel().getSelectedIndex();
			
			Game selectedGame = allGames.get(gameSelectionIndex);
			
			editedGame.setTeamA(selectedGame.getTeamA());
			editedGame.setTeamB(selectedGame.getTeamB());
			
			editedGame.setGoalsTeamA(Integer.parseInt(goalsTeamABox.getText()));
			editedGame.setGoalsTeamB(Integer.parseInt(goalsTeamBBox.getText()));
			editedGame.setGameFinished(true);
			
			if(editedGame.getId() != -1) {
				// update game
				
				EuroBetDbFactory.getInstance().updateGame(editedGame);
				
			
				System.out.println("Existing game updated: " + editedGame.toString());
			} else {
				// save new game
				
				Integer newId = EuroBetDbFactory.getInstance().addGame(editedGame);
				editedGame.setId(newId);
				System.out.println("New game saved: " + editedGame.toString());
				
			}
		});
		
		createDetailFields();
	}
	
	private void createDetailFields() {
		
		VBox details = new VBox();
		details.setSpacing(50);
		details.setId("result-detail-container");
		
		
		
		allGames = EuroBetDbFactory.getInstance().getGames();
		List<String> gameNames = new ArrayList<>();
		for(Game t: allGames) {
			gameNames.add(t.getTeamA().getName() + " - " + t.getTeamB().getName());
		}
        
        // games
        VBox game = new VBox();
 		game.setSpacing(3);
 		Label gameTitle = new Label("Game:");
 		
 		ObservableList<String> optionsB = FXCollections.observableList(gameNames);
 			    
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
		
		editedGame.clone(game);
		
		gameBox.getSelectionModel().select(game.getTeamA().getName() + " - " + game.getTeamB().getName());
		
		
		if(game.getGameFinished()) {
			goalsTeamABox.setText(game.getGoalsTeamA().toString());
			goalsTeamBBox.setText(game.getGoalsTeamB().toString());
		} else {
			goalsTeamABox.setText("");
			goalsTeamBBox.setText("");
		}
	}	
	
}
