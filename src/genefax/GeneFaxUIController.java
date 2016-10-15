/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genefax;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.ConditionalFeature.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    private AnchorPane content;
    
    @FXML
    private MenuItem about;
    
    @FXML
    private Button giveMeTheFacts;
    
    //@FXML
    //private MenuItem file;
    
    @FXML
    private MenuItem edit;
    
    @FXML
    private Button startNew;
    
    @FXML
    private Button SkipNew;
    
    @FXML
    private Button loadNew;
    
    @FXML
    public TextField conditionA;
    
    @FXML
    public TextField conditionB;
    
    @FXML
    public TextField conditionC;
    
    @FXML Button buttonA;
    
    @FXML Button buttonB;
    
    @FXML Button buttonC;
    
    @FXML
    private TextField replicants;
    
    @FXML
    private TextField newName;
    
    @FXML
    private Button submit;
    
    @FXML
    private TextArea geneDescription;
    
    @FXML
    private TableView listView;
    
    @FXML
    private ChoiceBox graphChoice;
    
    @FXML
    private Pane graph;
    
    @FXML
    private Button filter;
    
    @FXML
    private TextField foldchangeChoice;
    
    @FXML
    private TextField pvalueChoice;
    
    @FXML
    private MenuItem graphing;
    
    @FXML
    private MenuItem phoneHome;
    
    @FXML
    private Button returnHome;
    
    @FXML
    private Button triggerExample;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void load() {
        
        Alert load = new Alert(AlertType.INFORMATION);
        load.setTitle("About GeneFax");
        load.setContentText("GeneFax is a free way to analyze experiment results concerning genes.");
        load.showAndWait();
    }
    
    @FXML
    public void goHome(ActionEvent event) throws IOException{
     Stage stage; 
     Parent root;
     root = FXMLLoader.load(getClass().getResource("GeneFaxUI.fxml"));
     //create a new scene with root and set the stage
     stage =(Stage) content.getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }
    
    @FXML
    private void chooseNew(ActionEvent event) throws IOException{
        
     Stage stage; 
     Parent root;
      root = FXMLLoader.load(getClass().getResource("GeneFaxNewProject.fxml"));
     //create a new scene with root and set the stage
      stage=(Stage) content.getScene().getWindow();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    
    @FXML
    private void submitLoad(ActionEvent event) throws IOException{
        
     Stage stage; 
     Parent root;
      root = FXMLLoader.load(getClass().getResource("GeneFaxListView.fxml"));
     //create a new scene with root and set the stage
      stage =(Stage) content.getScene().getWindow();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    
     @FXML
    private void skipLoad(ActionEvent event) throws IOException{
        
     Stage stage; 
     Parent root;
      root = FXMLLoader.load(getClass().getResource("GeneFaxListView.fxml"));
     //create a new scene with root and set the stage
      stage =(Stage) content.getScene().getWindow();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    
    @FXML
    private void graphPage(ActionEvent event) throws IOException{
     Stage stage; 
     Parent root;
     root = FXMLLoader.load(getClass().getResource("GeneFaxGraphView.fxml"));
     //create a new scene with root and set the stage
     stage =(Stage) content.getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }
    
      @FXML
    private void instruct(ActionEvent event) throws IOException{
     Stage stage; 
     Parent root;
     root = FXMLLoader.load(getClass().getResource("InstructionImage.fxml"));
     //create a new scene with root and set the stage
     stage =(Stage) content.getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }
    
    @FXML
    private void chooseExperimentA() {
      FileChooser chooser = new FileChooser();
            Stage stage = (Stage) returnHome.getScene().getWindow();
            File filename = chooser.showOpenDialog(stage);
            conditionA.setText(filename.toString());
    }
    
      @FXML
    private void chooseExperimentB() {
      FileChooser chooser = new FileChooser();
            Stage stage = (Stage) returnHome.getScene().getWindow();
            File filename = chooser.showOpenDialog(stage);
            conditionB.setText(filename.toString());
    }
    
    @FXML
     private void chooseFold() {
      FileChooser chooser = new FileChooser();
            Stage stage = (Stage) returnHome.getScene().getWindow();
            File filename = chooser.showOpenDialog(stage);
            conditionC.setText(filename.toString());
     }
}
