package swe4.gui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class ToggleSwitch extends HBox {
	
	private final Label label = new Label();
	private final Button button = new Button();
	
	private SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(false);
	public SimpleBooleanProperty switchOnProperty() { return switchedOn; }
	
	private void init() {
		
		label.setText("OFF");
		
		getChildren().addAll(label, button);	
		button.setOnAction((e) -> {
			switchedOn.set(!switchedOn.get());
		});
		label.setOnMouseClicked((e) -> {
			switchedOn.set(!switchedOn.get());
		});
		setStyle();
		bindProperties();
	}
	
	private void setStyle() {
		// default width
		setWidth(80);
		label.setAlignment(Pos.CENTER);
		setStyle("-fx-background-color: lightgray; "
				+ "-fx-text-fill:black; "
				+ "-fx-background-radius: 0;");
		setAlignment(Pos.CENTER_LEFT);
	}
	
	private void bindProperties() {
		label.prefWidthProperty().bind(widthProperty().divide(2));
		label.prefHeightProperty().bind(heightProperty());
		button.prefWidthProperty().bind(widthProperty().divide(2));
		button.prefHeightProperty().bind(heightProperty());
	}
	
	public ToggleSwitch() {
		init();
		switchedOn.addListener((a,b,c) -> {
			if (c) {
                		switchOn();
            		}
            		else {
            			switchOff();
            		}
		});
	}
	
	public void switchOn() {
		label.setText("ON");
		setStyle("-fx-background-color: green;");
		label.toFront();
	}
	
	public void switchOff() {
		label.setText("OFF");
		setStyle("-fx-background-color: lightgray;");
    	button.toFront();
	}
	
	public void switchToStatus(Boolean status) {
		if(status) {
			switchOn();
		} else {
			switchOff();
		}
	}
	
}
