package swe4.gui.pages;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import swe4.gui.panels.ResultDetailPanel;
import swe4.gui.panels.ResultsPanel;
import swe4.gui.panels.ResultsPanelListener;
import swe4.models.Game;


public class ResultsPage extends FlowPane {
	
	
	private static final Background BACKGROUND = new Background
			(new BackgroundFill(Color.WHITE, null, null));
	

	
	public ResultsPage() {
		
		this.setBackground(BACKGROUND);
		

		this.setPadding(new Insets(5, 0, 5, 0));
		this.setVgap(20);
		this.setHgap(20);
		//this.setPrefWrapLength(1300); // preferred width allows for two columns
		this.setPrefWidth(1300);
		
		
		// all users panel
		ResultsPanel allResults = new ResultsPanel();
        
		// detail panel
		ResultDetailPanel detailResult = new ResultDetailPanel();
		
		// handle listeners
		allResults.addListener(new ResultsPanelListener() {

			@Override
			public void onResultSelected(Game game) {
				detailResult.setDetails(game);
			}
		});
        
        
        this.getChildren().addAll(allResults, detailResult);
	}
	
	

	
}
