package swe4.gui.panels;

import java.io.Serializable;
import java.rmi.Remote;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import swe4.gui.ToggleSwitch;
import swe4.gui.components.PanelComponent;
import swe4.models.User;
import swe4.rmi.client.EuroBetDbFactory;
import swe4.rmi.client.UserUpdatedListener;

public class UserDetailPanel extends PanelComponent implements Serializable, Remote  {


	
	private static final long serialVersionUID = -7267372017525019410L;
	private ToggleSwitch switchActiv;
	private TextField nameField;
	private PasswordField passwordField;
	private PasswordField confirmField;
	private BooleanBinding disableSaveCondition;

	private User editedUser = new User();
	
	public UserDetailPanel() {
		super("Details");
		
		
		createDetailFields();
		
		disableSaveCondition = Bindings.or(
				passwordField.textProperty().isNotEqualTo(confirmField.textProperty()), 
				nameField.textProperty().isEmpty()
		);
		
		addHeaderButtonRight("users-save-button", "/save.png", disableSaveCondition, event -> {
			
			
			// save or update user
			
			if(editedUser.getId() != -1) {
				// update user
				
				EuroBetDbFactory.getInstance().updateUser(editedUser);
			
				System.out.println("Existing user updated: " + editedUser.toString());
			} else {
				// save new user
				
				Integer newId = EuroBetDbFactory.getInstance().addUser(editedUser);
				editedUser.setId(newId);
				System.out.println("New user saved: " + editedUser.toString());
			}
		});
		
		
		
		EuroBetDbFactory.getInstance().addUserUpdatedListener(new UserUpdatedListener() {
			
			@Override
			public void onUserUpdated(User user) {
				if(editedUser.getId() == user.getId()) {
					// other client has changed current user
					editedUser.clone(user);
				}
			}
		});
		
		
		
	}
	
	private void createDetailFields() {
		
		VBox details = new VBox();
		details.setSpacing(20);
		details.setId("user-detail-container");
		
		
		// active
		VBox active = new VBox();
		Label activTitle = new Label("Active:");
		switchActiv = new ToggleSwitch();
		switchActiv.switchOnProperty().bindBidirectional(editedUser.getActiveProperty());
		
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
		nameField.textProperty().bindBidirectional(editedUser.getNameProperty());
		username.getChildren().addAll(nameTitle, nameField);
		VBox.setMargin(username, new Insets(0, 30, 0, 30));
		
		
		// password
		VBox password = new VBox();
		password.setSpacing(3);
		Label passwordTitle = new Label("Password:");
		passwordField = new PasswordField ();
		passwordField.textProperty().bindBidirectional(editedUser.getPasswordProperty());
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
		
		editedUser.clone(user);
		confirmField.setText(editedUser.getPassword());
	}
	
	public void createNewDetail() {
		
		editedUser.clone(new User());
		confirmField.setText(editedUser.getPassword());
	}

	
	
}
