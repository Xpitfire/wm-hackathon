package swe4.gui.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import swe4.gui.helper.GuiHelper;

public class SidebarComponent extends Pane {
	
	
	private static final Background BACKGROUND = new Background
			(new BackgroundFill(Color.web("0x21252b",1.0), null, null));
	
	private VBox navigationButtonsPane;
	
	
	public SidebarComponent() {
		
		// set background
		this.setBackground(BACKGROUND);
		
		// create panes
		Pane userPane = createUserPane();
		navigationButtonsPane = new VBox();
		navigationButtonsPane.setSpacing(20);
		navigationButtonsPane.setPadding(new Insets(100, 20, 0, 25));
		
		// create horizontal line
		Path path = new Path();
        path.getElements().add(new MoveTo(0.0f, 80.0f));
        path.getElements().add(new HLineTo(145.0f));
        path.setStroke(Color.WHITE);
		
        // add all elements
		this.getChildren().addAll(userPane, path, navigationButtonsPane);
	}
	
	
	private Pane createUserPane() {
		
		Label currentUserText = new Label("Welcome,\n Admin");
		currentUserText.setId("nav-welcome");
		
		Image currentUserImage = new Image("/avatar.png");
		
		ImageView currentUserAvatar = new ImageView();
		currentUserAvatar.setImage(currentUserImage);
		
		
		HBox userPane = new HBox(currentUserAvatar, currentUserText);
		userPane.setSpacing(5);
		userPane.setAlignment(Pos.CENTER);
		userPane.setPadding(new Insets(15,15,0,15));
		
		return userPane;
	}

	
	
	public void addNavigationButton(String title, String iconFile, 
			EventHandler<ActionEvent> handler) { 
		
		GuiHelper helper = new GuiHelper();
		Button button = helper.createTextIconButton("nav-button", title, iconFile);
		button.addEventHandler(ActionEvent.ACTION, handler);
		
		navigationButtonsPane.getChildren().add(button);	
	}
	
	
	
}
