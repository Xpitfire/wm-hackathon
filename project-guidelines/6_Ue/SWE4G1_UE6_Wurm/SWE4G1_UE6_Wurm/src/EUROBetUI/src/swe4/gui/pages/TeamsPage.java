package swe4.gui.pages;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import swe4.gui.panels.TeamDetailPanel;
import swe4.gui.panels.TeamsPanel;
import swe4.gui.panels.TeamsPanelListener;
import swe4.models.Team;


public class TeamsPage extends FlowPane {
	
	
	private static final Background BACKGROUND = new Background
			(new BackgroundFill(Color.WHITE, null, null));
	

	public TeamsPage() {
		
		this.setBackground(BACKGROUND);
		

		this.setPadding(new Insets(5, 0, 5, 0));
		this.setVgap(20);
		this.setHgap(20);
		//this.setPrefWrapLength(1300); // preferred width allows for two columns
		this.setPrefWidth(1300);
		
		
		// all users panel
		TeamsPanel allTeams = new TeamsPanel();
        
		// add detail panel
        TeamDetailPanel detailTeam = new TeamDetailPanel();
        
        // handle listeners
        allTeams.addListener(new TeamsPanelListener() {

			@Override
			public void onTeamSelected(Team team) {
				detailTeam.setDetails(team);
			}

			@Override
			public void onAddNewTeam() {
				detailTeam.createNewDetail();
			}
		});
        
        
        this.getChildren().addAll(allTeams, detailTeam);
	}
	
	

	
}
