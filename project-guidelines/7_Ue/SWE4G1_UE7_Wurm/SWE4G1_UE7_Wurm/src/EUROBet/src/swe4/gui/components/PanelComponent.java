package swe4.gui.components;


import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import swe4.gui.helper.GuiHelper;

public abstract class PanelComponent extends GridPane {
	

	private static final Border BORDER = new Border(
			new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, null, null)
		);
	
	private int numberOfLeftButtons = 0;
	private int numberOfRightButtons = 0;
	
	public PanelComponent(String title) {
		
		this.setBorder(BORDER);
		Rectangle header = new Rectangle();
		header.setX(0);
		header.setY(0);
		header.setWidth(400);
		header.setHeight(40);
		header.setFill(Color.CADETBLUE);
		
		
        Label panelTitle = new Label(title);
        panelTitle.setFont(Font.font("Helvetia", 20));
        panelTitle.setStyle("-fx-padding: 0px 0px 0px 150px; -fx-text-fill: #ffffff");
        

        
        Rectangle content = new Rectangle();
        content.setX(0);
        content.setY(0);
        content.setWidth(400);
        content.setHeight(450);
        content.setFill(Color.WHITE);
        
        
        // add all elements to panel
        
        this.add(header, 0, 0);
        this.add(panelTitle, 0, 0);
        this.add(content, 0, 1);
	}
	
	protected void addContent(Node component) {
		this.add(component, 0, 1);
	}
	
	
	protected void addHeaderButtonLeft(String id, 
			String iconFile, EventHandler<ActionEvent> handler) {
		GuiHelper helper = new GuiHelper();
		Button button = helper.createIconButton(id, iconFile);
		
		// calculate padding
		String paddingLeft;
		if(numberOfLeftButtons == 0) {
			paddingLeft = "10";
		} else {
			paddingLeft = Integer.toString(10 + numberOfLeftButtons * 40);
		}
		numberOfLeftButtons ++;
		
		// set properties
		button.setStyle("-fx-background-color: transparent; "
				+ "-fx-padding: 0px 0px 0px"+ 
				paddingLeft +"px");
		button.addEventHandler(ActionEvent.ACTION, handler);
		
		// add button to header
		this.add(button, 0, 0);
		
	}
		
	
	protected void addHeaderButtonRight(String id, String iconFile, 
			BooleanBinding disabled, EventHandler<ActionEvent> handler) {
		GuiHelper helper = new GuiHelper();
		Button button = helper.createIconButton(id, iconFile);
		
		if(disabled != null) {
			button.disableProperty().bind(disabled);
		}
		
		
		// calculate padding
		String paddingLeft;
		if(numberOfRightButtons == 0) {
			paddingLeft = "370";
		} else {
			paddingLeft = Integer.toString(370 - numberOfRightButtons * 40);
		}
		numberOfRightButtons ++;
		
		// set properties
		button.setStyle("-fx-background-color: transparent;"
				+ " -fx-padding: 0px 0px 0px"
				+ paddingLeft +"px");
		button.addEventHandler(ActionEvent.ACTION, handler);
		
		// add button to header
		this.add(button, 0, 0);
		
	}
	
	
	
}
