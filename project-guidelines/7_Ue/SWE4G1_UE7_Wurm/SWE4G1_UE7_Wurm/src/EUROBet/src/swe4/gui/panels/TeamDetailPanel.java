package swe4.gui.panels;

import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import swe4.gui.components.PanelComponent;
import swe4.models.Team;
import swe4.rmi.client.EuroBetDbFactory;
import swe4.rmi.client.TeamUpdatedListener;

public class TeamDetailPanel extends PanelComponent {

	
	private TextField nameField;
	private ComboBox<String> groupBox;
	private BooleanBinding disableSaveCondition;
	private Team editedTeam = new Team();
	

	public TeamDetailPanel() {
		super("Details");
		
		
		
		createDetailFields();
		
		
		disableSaveCondition = Bindings.and(
				nameField.textProperty().isEmpty(),
				nameField.textProperty().isEmpty()
		);
		
		
		addHeaderButtonRight("team-save-button", "/save.png", disableSaveCondition, event -> {
			
			// save or update team
			
			if(editedTeam.getId() != -1) {
				// update team
				
				EuroBetDbFactory.getInstance().updateTeam(editedTeam);
			
				System.out.println("Existing team updated: " + editedTeam.toString());
			} else {
				// save new user
				
				Integer newId = EuroBetDbFactory.getInstance().addTeam(editedTeam);
				editedTeam.setId(newId);
				System.out.println("New team saved: " + editedTeam.toString());
			}
			
		});
		
		
		EuroBetDbFactory.getInstance().addTeamUpdatedListener(new TeamUpdatedListener() {
			
			@Override
			public void onTeamUpdated(Team team) {
				if(editedTeam.getId() == team.getId()) {
					// other client has changed current user
					editedTeam.clone(team);
				}
			}
		});
	}
	
	private void createDetailFields() {
		
		VBox details = new VBox();
		details.setSpacing(20);
		details.setId("team-detail-container");
		
		
		
		// team name
		VBox username = new VBox();
		username.setSpacing(3);
		Label nameTitle = new Label("Country:");
		nameField = new TextField ();
		nameField.textProperty().bindBidirectional(editedTeam.getNameProperty());
		username.getChildren().addAll(nameTitle, nameField);
		VBox.setMargin(username, new Insets(30, 30, 0, 30));
		
		
		// team group
		VBox group = new VBox();
		group.setSpacing(3);
		Label groupTitle = new Label("Group:");
		List<String> groups = EuroBetDbFactory.getInstance().getGroups();
        ObservableList<String> options = FXCollections.observableList(groups);
		groupBox = new ComboBox<String>(options);
		groupBox.valueProperty().bindBidirectional(editedTeam.getGroupProperty());
		group.getChildren().addAll(groupTitle, groupBox);
		VBox.setMargin(group, new Insets(0, 30, 0, 30));

		
		details.getChildren().addAll(
				 username, group
		);
		
		
		addContent(details);
		
	}
	
	public void setDetails(Team team) {
		
		editedTeam.clone(team);
	}
	
	public void createNewDetail() {
		editedTeam.clone(new Team());
	}
	
	
}
