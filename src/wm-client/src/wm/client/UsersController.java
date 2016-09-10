package wm.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;

public class UsersController implements Initializable  {
	@FXML
	private ToggleButton addButton;
	
	@Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
       addButton.setOnAction(e -> {
    	   System.out.println("Button clicked!!!!");
       });	
    }
}
