package swe4.gui.panels;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import swe4.gui.components.PanelComponent;
import swe4.models.Game;
import swe4.models.Team;
import swe4.rmi.client.EuroBetDbFactory;

public class GameDetailPanel extends PanelComponent {

	
	private ComboBox<String> teamABox;
	private ComboBox<String> teamBBox;
	
	private BooleanBinding disableSaveCondition;
	private Game editedGame = new Game();
	private List<Team> allTeams;

	public GameDetailPanel() {
		super("Details");
		
		
		createDetailFields();
		
		disableSaveCondition = Bindings.or(
				teamABox.getSelectionModel().selectedItemProperty().isEqualTo(new Team().getNameProperty()),
				teamBBox.getSelectionModel().selectedItemProperty().isEqualTo(new Team().getNameProperty())
		);
		
		addHeaderButtonRight("game-save-button", "/save.png", disableSaveCondition, event -> {
			// save or update game
			
			Integer teamASelectionIndex =  teamABox.getSelectionModel().getSelectedIndex();
			editedGame.setTeamA(allTeams.get(teamASelectionIndex));
			
			
			Integer teamBSelectionIndex =  teamBBox.getSelectionModel().getSelectedIndex();
			editedGame.setTeamB(allTeams.get(teamBSelectionIndex));
			
			
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
		
		
		
	}
	
	private void createDetailFields() {
		
		VBox details = new VBox();
		details.setSpacing(50);
		details.setId("game-detail-container");
		
		
		allTeams = EuroBetDbFactory.getInstance().getTeams();
		List<String> teamNames = new ArrayList<>();
		for(Team t: allTeams) {
			teamNames.add(t.getName());
		}
		
		
		
		// team a
		VBox groupA = new VBox();
		groupA.setSpacing(3);
		Label groupATitle = new Label("Team A:");

        ObservableList<String> optionsA = FXCollections.observableList(teamNames);
		
		
		teamABox = new ComboBox<String>(optionsA);
		
		
		teamABox.setMinWidth(340);
		groupA.getChildren().addAll(groupATitle, teamABox);
		VBox.setMargin(groupA, new Insets(60, 30, 0, 30));
		
		
		// team b
		VBox groupB = new VBox();
		groupB.setSpacing(3);
		Label groupBTitle = new Label("Team B:");
		ObservableList<String> optionsB = FXCollections.observableList(teamNames);
		
		
		teamBBox = new ComboBox<>(optionsB);
		teamBBox.setMinWidth(340);
		groupB.getChildren().addAll(groupBTitle, teamBBox);
		VBox.setMargin(groupB, new Insets(0, 30, 0, 30));

		
		details.getChildren().addAll(
				 groupA, groupB
		);
		
		this.createNewDetail();
		
		addContent(details);
		
	}
	
	public void setDetails(Game game) {
		editedGame.clone(game);
		teamABox.getSelectionModel().select(game.getTeamA().getName());
		teamBBox.getSelectionModel().select(game.getTeamB().getName());
	}
	
	public void createNewDetail() {
		editedGame.clone(new Game());
		editedGame.setTeamA(new Team());
		editedGame.setTeamB(new Team());
		teamABox.getSelectionModel().select("");
		teamBBox.getSelectionModel().select("");
	}
}
