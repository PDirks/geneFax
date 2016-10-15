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
    
    /**
     * 
     * @param importPath full url path to csv data file. NOTE: FILE NEEDS TO 
     * COMPLY WITH DATA LAYOUT STANDARDS
     * @param replicantCount number of replicants, ie trials in a test
     */
    public ImportManager( String importPath, int replicantCount ) {
        this.importPath = importPath;
        this.replicantCount = replicantCount;
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
        final String delimiter = ",";
        ArrayList<GeneDataRow> ret = new ArrayList<GeneDataRow>(128);
        int rowCount = 0;
        
        try {
            br = new BufferedReader( new FileReader(this.importPath) );
            while((line = br.readLine()) != null){
                if( 0 == rowCount ){
                    String[] parsed_data = line.split(delimiter);
                    dataLabel = parsed_data[2];
                }
                else{
                    String[] parsed_data = line.split(delimiter);
                    
                    // if not enough data feilds are provided, return null
                    if( 4 > parsed_data.length ){
                        return null;
                    }
                    
                    GeneDataRow gdr = new GeneDataRow( parsed_data[0], 
                            parsed_data[1],
                            dataLabel,
                            new Float(parsed_data[1+this.replicantCount]).floatValue(),
                            new Float(parsed_data[2 + replicantCount]).floatValue()
                    );
                    for( int i = 0 ; i < replicantCount; i++ ){
                        gdr.addDataPoint(new Float(parsed_data[2 + i]).floatValue());
                    }
                    
                }
            }// end while
        }// end try
        catch( FileNotFoundException e ){
            e.printStackTrace();
            ret = null;
        }
        catch( IOException e) {
            e.printStackTrace();
            ret = null;
        }
        finally{
            if( br != null ){
                try {
                    br.close();
                }
                catch( IOException e ){
                    e.printStackTrace();
                    ret = null;
                }
            }// end is null check
        }
        
        return ret;
        
    }// end importCSV
    
    
}// end importManager
