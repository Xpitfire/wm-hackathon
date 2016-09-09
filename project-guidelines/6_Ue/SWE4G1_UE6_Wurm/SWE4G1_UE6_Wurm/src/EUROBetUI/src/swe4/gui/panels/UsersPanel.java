package swe4.gui.panels;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import swe4.gui.components.PanelComponent;
import swe4.models.User;


public class UsersPanel extends PanelComponent{
	
	private static final Border LIST_BORDER = new Border(
			new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, null, null)
		);
	
	
	private List<UsersPanelListener> listeners = new ArrayList<UsersPanelListener>();
	
	public UsersPanel() {
		super("All Users");
		
		addHeaderButtonLeft("users-add-button", "/plus.png", event -> {
			// call all added listeners
            for (UsersPanelListener l : listeners) {
                l.onAddNewUser();
            }	
		});
		createUsersList();
	}

	private void createUsersList() {
		ListView<User> personListView = new ListView<User>();
        personListView.setBorder(LIST_BORDER);
        
        List<User> users = new ArrayList<>();
        
        // create demo users
        User u1 = new User(true, "Robert Wurm", "1234");
        users.add(u1);
        
        User u2 = new User(true, "Administrator", "1234");
        users.add(u2);
        
        User u3 = new User(false, "Max Mustermann", "1234");
        users.add(u3);
        
        User u4 = new User(true, "Gertraud Eberharter", "1234");
        users.add(u4);
        
    
        
        ObservableList<User> items = FXCollections.observableList(users);
        
        
        personListView.setItems(items);
        
        
        personListView.setCellFactory(new Callback<ListView<User>, ListCell<User>>(){
        	 
            @Override
            public ListCell<User> call(ListView<User> p) {
                 
                ListCell<User> cell = new ListCell<User>(){
 
                    @Override
                    protected void updateItem(User t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                        	setText(t.getName());
                        }
                    }
                };
                return cell;
            }
        });
        
        personListView.getSelectionModel().selectedItemProperty()
        			.addListener(new ChangeListener<User>() {
        	
        	@Override
        	public void changed(ObservableValue<? extends User> observable, 
        			User oldValue, User newValue) {
        		
                // call all added listeners
                for (UsersPanelListener l : listeners) {
                    l.onUserSelected(newValue);
                }
        	}
        });
        
        addContent(personListView);
	}
	
	
	public void addListener(UsersPanelListener listener) {
		listeners.add(listener);
    }
	

}
