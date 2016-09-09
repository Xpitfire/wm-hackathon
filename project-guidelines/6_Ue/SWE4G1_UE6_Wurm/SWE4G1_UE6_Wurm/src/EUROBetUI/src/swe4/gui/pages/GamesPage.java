package swe4.gui.pages;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import swe4.gui.panels.GameDetailPanel;
import swe4.gui.panels.GamesPanel;
import swe4.gui.panels.GamesPanelListener;
import swe4.gui.panels.TeamDetailPanel;
import swe4.gui.panels.TeamsPanelListener;
import swe4.models.Game;
import swe4.models.Team;


public class GamesPage extends FlowPane {
		
	private static final Background BACKGROUND = new Background
			(new BackgroundFill(Color.WHITE, null, null));
	
	public GamesPage() {
		
		this.setBackground(BACKGROUND);
		

		this.setPadding(new Insets(5, 0, 5, 0));
		this.setVgap(20);
		this.setHgap(20);
		this.setPrefWidth(1300);
		
		
		// all users panel
		GamesPanel allGames = new GamesPanel();
		
		// add detail panel
        GameDetailPanel detailGame = new GameDetailPanel();
		
		// handle listeners
		allGames.addListener(new GamesPanelListener() {

			@Override
			public void onGameSelected(Game game) {
				detailGame.setDetails(game);
			}

			@Override
			public void onAddNewGame() {
				detailGame.createNewDetail();
			}
		});
        
        
        this.getChildren().addAll(allGames, detailGame);
	}
	
	

	
}
