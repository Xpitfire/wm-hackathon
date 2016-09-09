package swe4.gui.panels;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import swe4.gui.components.PanelComponent;
import swe4.models.Game;
import swe4.models.Team;


public class GamesPanel extends PanelComponent{
	
	private static final Border LIST_BORDER = new Border(
			new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, null, null)
		);
	
	
	private List<GamesPanelListener> listeners = new ArrayList<GamesPanelListener>();
	
	public GamesPanel() {
		super("All Games");
		
		
		addHeaderButtonLeft("games-add-button", "/plus.png", event -> {
			// System.out.println("games-add-button: "+ event.getSource());
			
			// call all added listeners
            for (GamesPanelListener l : listeners) {
                l.onAddNewGame();
            }
		});
		
		
		createGamesList();
	}


	private void createGamesList() {
		ListView<Game> gamesListView = new ListView<Game>();
		gamesListView.setBorder(LIST_BORDER);
        
        
        
        // create demo teams
		List<Game> games = new ArrayList<>();
		
        Team t1 = new Team("Austria", 'G');
        Team t2 = new Team("Spain", 'G');
        Team t3 = new Team("Island", 'G');
        Team t4 = new Team("Portugal", 'A');
        
        // create demo games
        Game g1 = new Game(t1, t2);
        Game g2 = new Game(t2, t3);
        Game g3 = new Game(t3, t4);
        Game g4 = new Game(t1, t4);
        games.add(g1);
        games.add(g2);
        games.add(g3);
        games.add(g4);
        
        
        ObservableList<Game> items = FXCollections.observableList(games);
        gamesListView.setItems(items);
        
        
        gamesListView.setCellFactory(new Callback<ListView<Game>, ListCell<Game>>(){
        	 
            @Override
            public ListCell<Game> call(ListView<Game> p) {
                 
                ListCell<Game> cell = new ListCell<Game>(){
 
                    @Override
                    protected void updateItem(Game t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                        	setText(t.getTeamA().getName() +  " - " + t.getTeamB().getName());
                        }
                    }
                };
                return cell;
            }
        });
        
        gamesListView.getSelectionModel().selectedItemProperty()
        	.addListener(new ChangeListener<Game>() {
        	
        	@Override
        	public void changed(ObservableValue<? extends Game> observable, 
        			Game oldValue, Game newValue) {
        		
                // call all added listeners
                for (GamesPanelListener l : listeners) {
                    l.onGameSelected(newValue);
                }
        	}
        });
        
        addContent(gamesListView);
	}
	
	
	public void addListener(GamesPanelListener listener) {
		listeners.add(listener);
    }
	

}
