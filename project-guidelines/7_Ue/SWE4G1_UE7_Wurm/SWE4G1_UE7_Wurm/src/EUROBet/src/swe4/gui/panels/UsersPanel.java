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
import swe4.models.User;
import swe4.rmi.client.EuroBetDbFactory;
import swe4.rmi.client.UserAddedListener;
import swe4.rmi.client.UserUpdatedListener;


public class UsersPanel extends PanelComponent {
	

	private static final Border LIST_BORDER = new Border(
			new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.NONE, null, null)
		);
	
	
	private ListView<User> personListView;
	private List<UsersPanelListener> listeners = new ArrayList<>();
	
	
	
	public UsersPanel() {
		super("All Users");
		
		addHeaderButtonLeft("users-add-button", "/plus.png", event -> {
			// call all added listeners
            for (UsersPanelListener l : listeners) {
                l.onAddNewUser();
            }	
		});
		createUsersList();
		
		
		
		EuroBetDbFactory.getInstance().addUserAddedListener(new UserAddedListener() {

			@Override
			public void onUserAdded(User user) {
				personListView = null;
				createUsersList();
			}
		});
		
		EuroBetDbFactory.getInstance().addUserUpdatedListener(new UserUpdatedListener() {
			
			@Override
			public void onUserUpdated(User user) {
				personListView = null;
				createUsersList();
			}
		});
	}

	private void createUsersList() {
		personListView = new ListView<User>();
        personListView.setBorder(LIST_BORDER);
        
        
        List<User> users = EuroBetDbFactory.getInstance().getUsers();
        
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
