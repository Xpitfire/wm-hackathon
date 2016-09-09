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
import swe4.models.Team;
import swe4.rmi.client.EuroBetDbFactory;
import swe4.rmi.client.TeamAddedListener;
import swe4.rmi.client.TeamUpdatedListener;


public class TeamsPanel extends PanelComponent{
	
	private static final Border LIST_BORDER = new Border(
			new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, null, null)
		);
	private ListView<Team> teamListView;
	
	private List<TeamsPanelListener> listeners = new ArrayList<TeamsPanelListener>();
	
	public TeamsPanel() {
		super("All Teams");
		
		
		addHeaderButtonLeft("teams-add-button", "/plus.png", event -> {
			// call all added listeners
            for (TeamsPanelListener l : listeners) {
                l.onAddNewTeam();
            }
		});
		
		
		createTeamsList();
		
		EuroBetDbFactory.getInstance().addTeamAddedListener(new TeamAddedListener() {
			
			@Override
			public void onTeamAdded(Team team) {
				teamListView = null;
				createTeamsList();
			}
		});
		
		EuroBetDbFactory.getInstance().addTeamUpdatedListener(new TeamUpdatedListener() {

			@Override
			public void onTeamUpdated(Team team) {				
				teamListView = null;
				createTeamsList();
			}
		});
		
	}


	private void createTeamsList() {
		teamListView = new ListView<Team>();
		teamListView.setBorder(LIST_BORDER);
        
        List<Team> teams = EuroBetDbFactory.getInstance().getTeams();
        
    
        
        ObservableList<Team> items = FXCollections.observableList(teams);
        
        
        teamListView.setItems(items);
        
        
        teamListView.setCellFactory(new Callback<ListView<Team>, ListCell<Team>>(){
        	 
            @Override
            public ListCell<Team> call(ListView<Team> p) {
                 
                ListCell<Team> cell = new ListCell<Team>(){
 
                    @Override
                    protected void updateItem(Team t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                        	setText(t.getName());
                        }
                    }
                };
                return cell;
            }
        });
        
        teamListView.getSelectionModel().selectedItemProperty()
        			.addListener(new ChangeListener<Team>() {
        	
        	@Override
        	public void changed(ObservableValue<? extends Team> observable, 
        			Team oldValue, Team newValue) {
        		
                // call all added listeners
                for (TeamsPanelListener l : listeners) {
                    l.onTeamSelected(newValue);
                }
        	}
        });
        
        addContent(teamListView);
	}
	
	
	public void addListener(TeamsPanelListener listener) {
		listeners.add(listener);
    }
	

}
