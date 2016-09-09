package swe4.gui.components;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class RouteComponent extends Pane {
	
	private static final Background BACKGROUND = new Background
			(new BackgroundFill(Color.WHITE, null, null));
	
	private Map<String, Pane> pages = new HashMap<String, Pane>();
	
	public RouteComponent() {
		
		//this.setPadding(new Insets(100));
		this.setBackground(BACKGROUND);
	}
	
	
	public void addPage(String url, Pane page) {
		pages.put(url, page);
	}
	
	public void showPage(String url) {
		Pane page = pages.get(url);
		
		if(page == null) {
			System.out.println("Unable to show Page with url: " + url);
			return;
		}
		page.setPadding(new Insets(50, 0, 0, 50));
		
		
		this.getChildren().clear();
		this.getChildren().add(page);
	}
	
	
	
}
