package swe4.gui.panels;

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
import swe4.models.Team;
import swe4.models.User;

public class TeamDetailPanel extends PanelComponent {

	
	private TextField nameField;
	private ComboBox<String> groupBox;
	private final String 	
		GROUP_A = "GROUPE A", GROUP_B = "GROUPE B", GROUP_C = "GROUPE C",
		GROUP_D = "GROUPE D", GROUP_E = "GROUPE E", GROUP_F = "GROUPE F",
		GROUP_G = "GROUPE G";

	public TeamDetailPanel() {
		super("Details");
		
		addHeaderButtonRight("team-save-button", "/save.png", event -> {
			System.out.println("team-save-button: "+ event.getSource());
		});
		
		createDetailFields();
	}
	
	private void createDetailFields() {
		
		VBox details = new VBox();
		details.setSpacing(20);
		details.setId("team-detail-container");
		
		//details.setPadding(new Insets(100));
		
		
		
		
		
		// team name
		VBox username = new VBox();
		username.setSpacing(3);
		Label nameTitle = new Label("Country:");
		nameField = new TextField ();
		username.getChildren().addAll(nameTitle, nameField);
		VBox.setMargin(username, new Insets(30, 30, 0, 30));
		
		
		// team group
		VBox group = new VBox();
		group.setSpacing(3);
		Label groupTitle = new Label("Group:");
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        GROUP_A,
			        GROUP_B,
			        GROUP_C,
			        GROUP_D,
			        GROUP_E,
			        GROUP_F,
			        GROUP_G
			    );
		groupBox = new ComboBox<String>(options);
		group.getChildren().addAll(groupTitle, groupBox);
		VBox.setMargin(group, new Insets(0, 30, 0, 30));

		
		details.getChildren().addAll(
				 username, group
		);
		
		
		addContent(details);
		
	}
	
	public void setDetails(Team team) {
		if(team == null) { return; }
	
		nameField.setText(team.getName());
		
		if(team.getGroup() == 'A') {
			groupBox.setValue(GROUP_A);
			
		} else if(team.getGroup() == 'B') {
			groupBox.setValue(GROUP_B);
			
		} else if(team.getGroup() == 'C') {
			groupBox.setValue(GROUP_C);
			
		} else if(team.getGroup() == 'D') {
			groupBox.setValue(GROUP_D);
			
		} else if(team.getGroup() == 'E') {
			groupBox.setValue(GROUP_E);
			
		} else if(team.getGroup() == 'F') {
			groupBox.setValue(GROUP_F);
			
		} else if(team.getGroup() == 'G') {
			groupBox.setValue(GROUP_G);
		}
		
	}
	
	public void createNewDetail() {
		nameField.setText("");
		groupBox.setValue("");
		
	}
	
	
}
