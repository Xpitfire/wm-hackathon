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
import swe4.rmi.client.EuroBetDbFactory;
import swe4.rmi.client.GameAddedListener;
import swe4.rmi.client.GameUpdatedListener;


public class ResultsPanel extends PanelComponent{
	
	private static final Border LIST_BORDER = new Border(
			new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, null, null)
		);
	
	private  ListView<Game> gamesListView;
	
	private List<ResultsPanelListener> listeners = new ArrayList<ResultsPanelListener>();
	
	public ResultsPanel() {
		super("All Results");
		
		
		createGamesList();
		
		
		
		EuroBetDbFactory.getInstance().addGamesAddedListener(new GameAddedListener() {

			@Override
			public void onGameAdded(Game game) {
				gamesListView = null;
				createGamesList();
			}
		});
		
		EuroBetDbFactory.getInstance().addGamesUpdatedListener(new GameUpdatedListener() {
			
			@Override
			public void onGameUpdated(Game game) {
				gamesListView = null;
				createGamesList();
			}
		});
		
	}


	private void createGamesList() {
		gamesListView = new ListView<Game>();
		gamesListView.setBorder(LIST_BORDER);
        
        List<Game> games = EuroBetDbFactory.getInstance().getGames();
        
       
        ObservableList<Game> items = FXCollections.observableList(games);
        
        
        
        gamesListView.setItems(items);
        
        
        gamesListView.setCellFactory(new Callback<ListView<Game>, ListCell<Game>>(){
        	 
            @Override
            public ListCell<Game> call(ListView<Game> p) {
                 
                ListCell<Game> cell = new ListCell<Game>(){
 
                    @Override
                    protected void updateItem(Game g, boolean bln) {
                        super.updateItem(g, bln);
                        if (g != null) {
                        	setText(g.getResult() + "    " + g.getTeamA().getName() + " - " + g.getTeamB().getName());
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
                for (ResultsPanelListener l : listeners) {
                    l.onResultSelected(newValue);
                }
        	}
        });
        
        addContent(gamesListView);
	}
	
	
	public void addListener(ResultsPanelListener listener) {
		listeners.add(listener);
    }
	

}
