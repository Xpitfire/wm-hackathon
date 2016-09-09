package swe4.gui.helper;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import swe4.models.Game;

public class GuiHelper {
	
	public Button createIconButton(String id, String iconFile) {
		Button button = new Button();
		button.setId(id);
		button.setPadding(new Insets(10));
		Image icon = new Image(getClass().getResourceAsStream(iconFile));
		button.setGraphic(new ImageView(icon));

		return button;
	}
	
	public Button createTextButton(String id, String caption) {
		Button button = new Button(caption);
		button.setId(id);
		button.setPadding(new Insets(10));
		button.setPrefSize(50, 40);
		return button;
	}
	
	public Button createTextIconButton(String id, String caption, String iconFile) {
		Button button = new Button(caption);
		button.setId(id);
		button.setPadding(new Insets(10));
		Image icon = new Image(getClass().getResourceAsStream(iconFile));
		button.setGraphic(new ImageView(icon));

		return button;
	}
	

}
