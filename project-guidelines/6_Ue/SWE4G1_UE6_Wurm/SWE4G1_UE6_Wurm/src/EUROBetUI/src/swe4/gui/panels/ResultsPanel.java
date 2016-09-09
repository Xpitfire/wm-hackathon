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
import swe4.gui.helper.GuiHelper;
import swe4.models.Game;
import swe4.models.Team;


public class ResultsPanel extends PanelComponent{
	
	private static final Border LIST_BORDER = new Border(
			new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, null, null)
		);
	
	
	private List<ResultsPanelListener> listeners = new ArrayList<ResultsPanelListener>();
	
	public ResultsPanel() {
		super("All Results");
		
		addHeaderButtonLeft("results-add-button", "/plus.png", event -> {
			// System.out.println("results-add-button: "+ event.getSource());
			// call all added listeners
            for (ResultsPanelListener l : listeners) {
                l.onAddNewResult();
            }
		});
		
		
		createGamesList();
	}


	private void createGamesList() {
		ListView<Game> gamesListView = new ListView<Game>();
		gamesListView.setBorder(LIST_BORDER);
        
        List<Game> games = new ArrayList<>();
        
        // create demo teams
        Team t1 = new Team("Austria", 'G');
        Team t2 = new Team("Spain", 'G');
        Team t3 = new Team("Island", 'G');
        Team t4 = new Team("Portugal", 'A');
        
        // create demo games which are finished
        Game g1 = new Game(t1, t2);
        g1.setGameFinished(true);
        g1.setGoalsTeamA(2);
        g1.setGoalsTeamB(0);
        
        Game g2 = new Game(t2, t3);
        g2.setGameFinished(true);
        g2.setGoalsTeamA(4);
        g2.setGoalsTeamB(1);
        
        Game g3 = new Game(t3, t4);
        g3.setGameFinished(true);
        g3.setGoalsTeamA(0);
        g3.setGoalsTeamB(3);
        
        Game g4 = new Game(t1, t4);
        g4.setGameFinished(true);
        g4.setGoalsTeamA(2);
        g4.setGoalsTeamB(2);
        
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
                    protected void updateItem(Game g, boolean bln) {
                        super.updateItem(g, bln);
                        if (g != null) {
                        	setText(g.getResult() + "    " + g.toString());
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
