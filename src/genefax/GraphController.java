/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genefax;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 *
 * @author Aaron
 */

public class GraphController extends Application {
    
    @Override public void start(Stage stage) {
        stage.setTitle("Volcano Plot");
        final NumberAxis theXAxis = new NumberAxis(0,2000,1);
        final NumberAxis theYAxis = new NumberAxis (0,10,0.1);
        
        
        final ScatterChart<Number,Number> scatterChart = new ScatterChart<Number,Number>(theXAxis,theYAxis);
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
    
    public static void main(String[] args) {
        launch(args);
    }
}
