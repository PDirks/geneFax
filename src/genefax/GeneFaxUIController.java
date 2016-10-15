/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genefax;

import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.ConditionalFeature.FXML;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author bgbal_000
 */
public class GeneFaxUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private MenuItem about;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        
    public void load() {
        
        Alert load = new Alert(AlertType.INFORMATION);
        load.setTitle("About GeneFax");
        load.setContentText("GeneFax is a free way to analyze experiement results concerning genes.");
        load.showAndWait();
        
        
    }
}
