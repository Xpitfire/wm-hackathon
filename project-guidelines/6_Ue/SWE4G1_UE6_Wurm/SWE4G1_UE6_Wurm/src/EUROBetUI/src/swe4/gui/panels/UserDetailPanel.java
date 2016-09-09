package swe4.gui.panels;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import swe4.gui.ToggleSwitch;
import swe4.gui.components.PanelComponent;
import swe4.models.User;

public class UserDetailPanel extends PanelComponent {

	private ToggleSwitch switchActiv;
	private TextField nameField;
	private PasswordField passwordField;
	private PasswordField confirmField;

	public UserDetailPanel() {
		super("Details");
		
		addHeaderButtonRight("users-save-button", "/save.png", event -> {
			System.out.println("users-save-button: "+ event.getSource());
		});
				
		createDetailFields();
	}
	
	private void createDetailFields() {
		
		VBox details = new VBox();
		details.setSpacing(20);
		details.setId("user-detail-container");
		
		//details.setPadding(new Insets(100));
		
		
		
		// active
		VBox active = new VBox();
		Label activTitle = new Label("Active:");
		switchActiv = new ToggleSwitch();
		VBox switchPane = new VBox();
		switchPane.getChildren().add(switchActiv);
		VBox.setMargin(switchPane, new Insets(-20, 0, 0, 250));
		active.getChildren().addAll(activTitle, switchPane);
		VBox.setMargin(active, new Insets(20, 30, 0, 30));
		
		
		// username
		VBox username = new VBox();
		username.setSpacing(3);
		Label nameTitle = new Label("Username:");
		nameField = new TextField ();
		username.getChildren().addAll(nameTitle, nameField);
		VBox.setMargin(username, new Insets(0, 30, 0, 30));
		
		
		// password
		VBox password = new VBox();
		password.setSpacing(3);
		Label passwordTitle = new Label("Password:");
		passwordField = new PasswordField ();
		Label confirmTitle = new Label("Confirm:");
		confirmField = new PasswordField ();
		password.getChildren().addAll(passwordTitle, passwordField, 
				confirmTitle, confirmField);
		VBox.setMargin(password, new Insets(0, 30, 0, 30));
		
		details.getChildren().addAll(
				active, username, password
		);
		
		
		addContent(details);
		
	}
	
	public void setDetails(User user) {
		if(user == null) { return; }
		switchActiv.switchToStatus(user.getActive());
		nameField.setText(user.getName());
		passwordField.setText(user.getPassword());
		confirmField.setText(user.getPassword());
	}
	
	public void createNewDetail() {
		switchActiv.switchToStatus(false);
		nameField.setText("");
		passwordField.setText("");
		confirmField.setText("");
	}
	
	
}
