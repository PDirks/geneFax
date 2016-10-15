/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genefax;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author pete
 */
public class GeneFax extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
       
        TestController tc = new TestController();
        tc.runTests();
        
        try{
            Parent root = FXMLLoader.load(getClass().getResource("GeneFaxUI.fxml"));
            Image icon = new Image(getClass().getResourceAsStream("icon.png"));
            primaryStage.getIcons().add(icon);
            Scene scene = new Scene(root);
            primaryStage.setTitle("GeneFax");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch( Exception e ){}
        
        
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        
    }
    
}
