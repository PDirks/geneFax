/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genefax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author pete
 */
public class ImportManager {
    
    private String importPath;
    private int replicantCount;
//    private int numConditions;
    final private int MIN_FLD_COUNT = 3;
    
    private String dataPath = "/home/pete/Documents/biochem_data/test_data_a.csv";
    private String relationPath = "/home/pete/Documents/biochem_data/test_data_c.csv";
    
    /**
     * 
     * @param importPath full url path to csv data file. NOTE: FILE NEEDS TO 
     * COMPLY WITH DATA LAYOUT STANDARDS
     * @param replicantCount number of replicants, ie trials in a test
     */
//    public ImportManager( String importPath, int replicantCount, int numConditions ) {
    public ImportManager( String importPath, int replicantCount ) {
        this.importPath = importPath;
        this.replicantCount = replicantCount;
//        this.numConditions = numConditions;
    }
    
    public ImportManager(){}
    
    public void setReplicantPath(int replicantCount){
        this.replicantCount = replicantCount;
    }
    
    public void setImportPath( String importPath ){
        this.importPath = importPath;
    }
    
    /**
     * 
     * @return arraylist of gene data objects; if invalid read, return NULL
     */
    public ArrayList<GeneDataRow> importCSV(){
        BufferedReader br = null;
        String line = "";
        String dataLabel = "";
//        ArrayList<String> dataLabels;
        final String delimiter = ",";
        ArrayList<GeneDataRow> ret = new ArrayList<GeneDataRow>(128);
        int rowCount = 0;
        
        try {
            br = new BufferedReader( new FileReader( dataPath ) );
            while((line = br.readLine()) != null){
                if( 0 == rowCount ){
                    String[] parsed_data = line.split(delimiter);
                    dataLabel = parsed_data[2];
//                    dataLabels = new ArrayList<String>(128);
//                    for(int i = 0; i < numConditions; i++){
//                        dataLabels.add(parsed_data[2 + i * replicantCount]);
//                    }
                    
                }
                else{
                    String[] parsed_data = line.split(delimiter);
                    
                    // if not enough data feilds are provided, return null
                    if( 0 == rowCount && MIN_FLD_COUNT > parsed_data.length ){
                        System.out.println("NULL!");
                        return null;
                    }
//                    System.out.println("getting "+parsed_data[1] + dataLabel);
//                    GeneDataRow gdr = new GeneDataRow( 
//                            parsed_data[0], 
//                            parsed_data[1],
//                            new Float(parsed_data[1+this.replicantCount]).floatValue(),
//                            new Integer(this.numConditions),
//                            new Float(parsed_data[2 + numConditions + numConditions * replicantCount]).floatValue(),
//                            new Float(parsed_data[3 + numConditions + numConditions * replicantCount]).floatValue()
//                    );

                    GeneDataRow gdr = new GeneDataRow( 
                            parsed_data[0], 
                            parsed_data[1],
                            dataLabel,
                            new Float(parsed_data[1+this.replicantCount]).floatValue()
                    );

                    for( int i = 0 ; i < replicantCount; i++ ){
                        gdr.addDataPoint(new Float(parsed_data[2 + i]).floatValue());
                    }
                    
//                    for( int i = 0; i < numConditions; i++ ){
//                        gdr.addAvgs(new Float(parsed_data[2 + numConditions * replicantCount]).floatValue());
//                    }
//                    
//                    int avg_spacing = 1;
//                    for( int i = 0 ; i < numConditions; i++ ){
//                        for(int j = 0; j < replicantCount; j++){
//                            gdr.addDataPoint( new Float(parsed_data[ 2 + avg_spacing + 2 * i + j ]).floatValue() );
//                        }
//                        avg_spacing++;
//                    }
                    ret.add(gdr);
                }// end else
                rowCount++;
            }// end while
        }// end try
        catch( FileNotFoundException e ){
            e.printStackTrace();
            ret = null;
            System.out.println("NULL!");
        }
        catch( IOException e) {
            e.printStackTrace();
            ret = null;
            System.out.println("NULL!");
        }
        finally{
            if( br != null ){
                try {
                    br.close();
                }
                catch( IOException e ){
                    e.printStackTrace();
                    ret = null;
                    System.out.println("NULL!");
                }
            }// end is null check
        }
        return ret;
        
    }// end importCSV
    
    public ArrayList<GeneRelation> importRelationCSV(){
        BufferedReader br = null;
        String line = "";
        String dataLabel = "";
        final String delimiter = ",";
        ArrayList<GeneRelation> ret = new ArrayList<GeneRelation>(128);
        int rowCount = 0;
        
        try {
            br = new BufferedReader( new FileReader( relationPath ) );
            while((line = br.readLine()) != null){
                
                if(rowCount == 0){
                    rowCount++;
                    continue;
                }
                String[] parsed_data = line.split(delimiter);
                System.out.println("debug~~~~~"+line+" xxxxxx "+parsed_data[2]);
                
                String t0 = parsed_data[2];
                t0.replace('−', '-');
                String t1 = parsed_data[3];
                t1.replace('−', '-');
                System.out.println("debug~~~~~"+line+" xxxxxx "+t0);
                GeneRelation gr = new GeneRelation(
                        parsed_data[0],
                        parsed_data[1],
                        Float.parseFloat(t0),
                        Float.parseFloat(t1)
                );
                ret.add(gr);
            }// end while
        }// end try
        catch( FileNotFoundException e ){
            e.printStackTrace();
            ret = null;
            System.out.println("NULL!");
        }
        catch( IOException e) {
            e.printStackTrace();
            ret = null;
            System.out.println("NULL!");
        }
        finally{
            if( br != null ){
                try {
                    br.close();
                }
                catch( IOException e ){
                    e.printStackTrace();
                    ret = null;
                    System.out.println("NULL!");
                }
            }// end is null check
        }
        return ret;
        
    }// end importRelationCSV
    
    
}// end importManager
