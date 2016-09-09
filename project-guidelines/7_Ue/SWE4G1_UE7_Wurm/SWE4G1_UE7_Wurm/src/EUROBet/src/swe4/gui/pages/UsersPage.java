package swe4.gui.pages;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import swe4.gui.panels.UserDetailPanel;
import swe4.gui.panels.UsersPanel;
import swe4.gui.panels.UsersPanelListener;
import swe4.models.User;


public class UsersPage extends FlowPane {
	
	
	private static final Background BACKGROUND = new Background
			(new BackgroundFill(Color.WHITE, null, null));
	

	public UsersPage() {
		
		this.setBackground(BACKGROUND);
		

		this.setPadding(new Insets(5, 0, 5, 0));
		this.setVgap(20);
		this.setHgap(20);
		//this.setPrefWrapLength(1300); // preferred width allows for two columns
		this.setPrefWidth(1300);
		
		
		// all users panel
		UsersPanel allUsers = new UsersPanel();
		
		
        // add detail panel
        UserDetailPanel detailUser = new UserDetailPanel();
        
        
        // handle listeners
        allUsers.addListener(new UsersPanelListener() {

			@Override
			public void onUserSelected(User user) {
				detailUser.setDetails(user);
			}

			@Override
			public void onAddNewUser() {
				detailUser.createNewDetail();
			}
		});
        
		
        
        this.getChildren().addAll(allUsers, detailUser);
	}
}
