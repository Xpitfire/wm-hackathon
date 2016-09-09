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
import swe4.gui.ToggleSwitch;
import swe4.gui.components.PanelComponent;
import swe4.models.Game;
import swe4.models.Team;
import swe4.models.User;

public class GameDetailPanel extends PanelComponent {

	
	private ComboBox<String> teamABox;
	private ComboBox<String> teamBBox;
	
	ArrayList<Team> teams = new ArrayList<Team>();

	public GameDetailPanel() {
		super("Details");
		
		addHeaderButtonRight("game-save-button", "/save.png", event -> {
			System.out.println("game-save-button: "+ event.getSource());
		});
		
		createDetailFields();
	}
	
	private void createDetailFields() {
		
		VBox details = new VBox();
		details.setSpacing(50);
		details.setId("game-detail-container");
		
		
        
		// create demo teams
		List<Game> games = new ArrayList<>();
		
        Team t1 = new Team("Austria", 'G');
        Team t2 = new Team("Spain", 'G');
        Team t3 = new Team("Island", 'G');
        Team t4 = new Team("Portugal", 'A');
        
		
		// team a
		VBox groupA = new VBox();
		groupA.setSpacing(3);
		Label groupATitle = new Label("Team A:");
		ObservableList<String> optionsA = 
			    FXCollections.observableArrayList(
			    		t1.getName(),
	 			        t2.getName(),
	 			        t3.getName(),
	 			        t4.getName()
			    );
		teamABox = new ComboBox<String>(optionsA);
		teamABox.setMinWidth(340);
		groupA.getChildren().addAll(groupATitle, teamABox);
		VBox.setMargin(groupA, new Insets(60, 30, 0, 30));
		
		
		
		// team b
		VBox groupB = new VBox();
		groupB.setSpacing(3);
		Label groupBTitle = new Label("Team B:");
		ObservableList<String> optionsB = 
			    FXCollections.observableArrayList(
			        t1.getName(),
			        t2.getName(),
			        t3.getName(),
			        t4.getName()
			    );
		teamBBox = new ComboBox<String>(optionsB);
		teamBBox.setMinWidth(340);
		groupB.getChildren().addAll(groupBTitle, teamBBox);
		VBox.setMargin(groupB, new Insets(0, 30, 0, 30));

		
		details.getChildren().addAll(
				 groupA, groupB
		);
		
		
		addContent(details);
		
	}
	
	public void setDetails(Game game) {
		if(game == null) { return; }
		teamABox.setValue(game.getTeamA().getName());
		teamBBox.setValue(game.getTeamB().getName());
	}
	
	public void createNewDetail() {
		teamABox.setValue("");
		teamBBox.setValue("");
	}
	
	
}
