/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genefax;
import java.util.ArrayList;

/**
 *
 * @author pete
 */
public class GeneDataRow {
    final private int arrayListCount = 128;
    
    private String geneName;
    private String geneID;
    private ArrayList<Float> dataPoints;
    private String dataLabel;
    private Float dataAvg;
    private Float p;
    private Float foldChange;
    private int numConditions;
    
    public GeneDataRow( String geneName, String geneID, String dataLabel, float avg){
        this.geneName = geneName;
        this.geneID = geneID;
        this.dataPoints = new ArrayList<Float>(arrayListCount);
        this.dataLabel = dataLabel;
        this.dataAvg = avg;
        this.p = 0f;
        this.foldChange = 0f;
    }
    
    public GeneDataRow( String geneName, String geneID, String dataLabel, float avg, float p){
        this( geneName, geneID, dataLabel, avg );
        this.p = p;
    }
    
    public GeneDataRow( String geneName, String geneID, String dataLabel, float avg, int numConditions){
        this( geneName, geneID, dataLabel, avg );
        this.numConditions = numConditions;
    }
    
    public boolean addDataPoint( float data ){
        dataPoints.add(data);
        return true;
    }
    
    public String getGeneName(){
        return this.geneName;
    }
    
    public String getGeneID(){
        return this.geneID;
    }
    
    public ArrayList<Float> getDataPoints(){
        return this.dataPoints;
    }
    
    public String getDataLabel(){
        return this.dataLabel;
    }
    
    public float getDataAvg(){
        return this.dataAvg;
    }
    
    public String getDataAvgStr(){
        return this.dataAvg.toString();
    }
 
    public int getNumConditions(){
        return this.numConditions;
    }
    
    public void calcP(){
        // TODO
    }
    
    public void calcFold(){
        // TODO
    }
    
    public float getP(){
        //TODO
        return this.p;
    }
    
    public float getFoldChange(){
        return this.foldChange;
    }
    
}
