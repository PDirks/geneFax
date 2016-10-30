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
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
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
    
    //Initializes the controller class.
    @FXML
    private AnchorPane content;
    
    @FXML
    private Button triggerHomeViewBtn;
    
    @FXML
    private Button triggerAboutBtn;
    
    @FXML
    private MenuItem triggerAboutMenu;
            
    @FXML
    private Button triggerNewProjectViewBtn;
    
    @FXML
    private Button triggerTableViewBtn;
    
    @FXML
    private Button loadFileBtn;
    
    @FXML
    public TextField conditionAFilename;
    
    @FXML
    public TextField conditionBFilename;
    
    @FXML
    public TextField foldchangeFileName;
    
    @FXML 
    private Button triggerConditionABtn;
    
    @FXML 
    private Button triggerConditionBBtn;
    
    @FXML
    private TextField numOfReplicates;
    
    @FXML
    private TextField comparisonFileName;
    
    @FXML
    private Button submitBtn;
    
    @FXML
    private TextArea geneDescription;
    
    @FXML
    private TableView table;
    
    @FXML
    private ChoiceBox graphChoice;
    
    @FXML
    private Pane graph;
    
    @FXML
    private Button triggerFilterBtn;
    
    @FXML
    private Button triggerFoldchangeChoiceBtn;
    
    @FXML
    private TextField foldchangeChoice;
    
    @FXML
    private TextField pvalueChoice;
    
    @FXML
    private MenuItem triggerGraphViewMenu;
    
    @FXML
    private MenuItem triggerHomeViewMenu;
    
    @FXML
    private Button triggerExampleViewBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    //The about function is used to give a short description of the app.
    @FXML
    public void about() {
        Alert load = new Alert(AlertType.INFORMATION);
        load.setTitle("About GeneFax");
        load.setContentText("GeneFax is a free way to analyze experiment results concerning genes.");
        load.showAndWait();
    }
    
    //The goHome function is used to return to the home page from any other page.
    @FXML
    public void goToHome(ActionEvent event) throws IOException{
     Stage stage; 
     Parent root;
     root = FXMLLoader.load(getClass().getResource("GeneFaxUI.fxml"));
     //create a new scene with root and set the stage
     stage =(Stage) content.getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }
    
    //If the user chooses to upload a new project, the new project page will open
    @FXML
    private void goToNew(ActionEvent event) throws IOException{
        
     Stage stage; 
     Parent root;
      root = FXMLLoader.load(getClass().getResource("GeneFaxNewProject.fxml"));
     //create a new scene with root and set the stage
      stage=(Stage) content.getScene().getWindow();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    
    /*If the user decides to upload an old project, the page goes to the gene 
      table view*/
    @FXML
    private void chooseLoad(ActionEvent event) throws IOException{
        
     Stage stage; 
     Parent root;
      root = FXMLLoader.load(getClass().getResource("GeneFaxTableView.fxml"));
     //create a new scene with root and set the stage
      stage =(Stage) content.getScene().getWindow();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    
    //Switch UI to the table view
    @FXML
    private void goToTable(ActionEvent event) throws IOException{    
     Stage stage; 
     Parent root;
      root = FXMLLoader.load(getClass().getResource("GeneFaxTableView.fxml"));
     //create a new scene with root and set the stage
      stage =(Stage) content.getScene().getWindow();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }
    
    //Switch UI to the graph view
    @FXML
    private void goToGraph(ActionEvent event) throws IOException{
     Stage stage; 
     Parent root;
     root = FXMLLoader.load(getClass().getResource("GeneFaxGraphView.fxml"));
     //create a new scene with root and set the stage
     stage =(Stage) content.getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }
    
    //Switch UI to the "example" view
    @FXML
    private void goToExample(ActionEvent event) throws IOException{
     Stage stage; 
     Parent root;
     root = FXMLLoader.load(getClass().getResource("GeneFaxInstructionImage.fxml"));
     //create a new scene with root and set the stage
     stage =(Stage) content.getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();
    }
    
    //Load the first condition file
    @FXML
    private void chooseConditionAFile() {
      FileChooser chooser = new FileChooser();
            Stage stage = (Stage) triggerHomeViewBtn.getScene().getWindow();
            File filename = chooser.showOpenDialog(stage);
            conditionAFilename.setText(filename.toString());
    }
    
    //Load the second condition file
    @FXML
    private void chooseConditionBFile() {
      FileChooser chooser = new FileChooser();
            Stage stage = (Stage) triggerHomeViewBtn.getScene().getWindow();
            File filename = chooser.showOpenDialog(stage);
            conditionBFilename.setText(filename.toString());
    }
    
    //Load the foldchange file
    @FXML
     private void chooseFoldchangeFile() {
      FileChooser chooser = new FileChooser();
            Stage stage = (Stage) triggerHomeViewBtn.getScene().getWindow();
            File filename = chooser.showOpenDialog(stage);
            foldchangeFileName.setText(filename.toString());
     }
     
     //Used for graphing on the graph view
     @FXML
     private void VolcanoGraph() {
        
        Stage stage =(Stage) content.getScene().getWindow();
        
        final NumberAxis theXAxis = new NumberAxis(0,2000,1);
        final NumberAxis theYAxis = new NumberAxis (0,10,0.1);
        
        final ScatterChart<Number,Number> scatterChart = new ScatterChart<>(theXAxis,theYAxis);
        theXAxis.setLabel("Foldchange");
        theYAxis.setLabel("q-value");
        scatterChart.setTitle("Volcano Plot");
        
        //final String SELECT_QUERY = "SELECT "
        XYChart.Series dots = new XYChart.Series();
        dots.setName("Gene");
        dots.getData().add(new XYChart.Data(100, 5));
        
        scatterChart.getData().addAll(dots);
        Scene scene = new Scene(scatterChart, 500, 400);
        stage.setScene(scene);
        stage.show();
    }
     
     @FXML
     protected void retrieveTableData() {
         
     }
}


