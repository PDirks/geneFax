/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genefax;

import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author pete
 */
public class DBmanager extends GeneFax {
    
    DBmanager(){}
    
    public String dbInit(){
        Connection c = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:GeneFax.db");
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        return "Opened database successfully";
    }

    
    public void createTable(){
        Connection c = null;
        Statement stmt = null;
        Statement stmt2 = null;
        Statement stmt3 = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:GeneFax.db");
            //System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String genes = "CREATE TABLE GENES" +
                           "(GENE_ID INT PRIMARY KEY," +
                           "GENE_NAME TEXT NOT NULL," + 
                           "DATA_LABEL TEXT," + 
                           "DATA_AVG FLOAT(2));";
             
            stmt.executeUpdate(genes);
            stmt.close();
             
            stmt2 = c.createStatement();
            String geneID = "CREATE TABLE ID" +
                            "(DATA_POINTS FLOAT(4)," +
                            "GENE_ID INT PRIMARY KEY REFERENCES GENES(GENE_ID));";
            stmt2.executeUpdate(geneID);
            stmt2.close();
             
            stmt3 = c.createStatement();
            String geneFold = "CREATE TABLE FOLD" +
                              "(FOLDS FLOAT(2)," +
                              "GENE_ID PRIMARY KEY REFERENCES GENES(GENE_ID));";
            
            stmt3.executeUpdate(geneFold);
            stmt3.close();
             
            c.close();
             
            } catch ( Exception e ) {
             System.err.println( e.getClass().getName() + ": " + e.getMessage() );
             System.exit(0);
            };
        System.out.println("Table created successfully");
    }
    
    public void insert(){
        ImportManager csvData = new ImportManager();
        ArrayList<GeneDataRow> data = new ArrayList<GeneDataRow>(100);
        
        data = csvData.importCSV();
        Connection c = null;
        Statement stmt = null;
        int i;
        int j;
        String sql;
        
        try{
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:GeneFax.db");
          c.setAutoCommit(false);
          System.out.println("Opened database successfully");
          
          if(data != null){   
              
            for(i = 0; i < data.size(); i++){
                stmt = c.createStatement();
                sql = "INSERT INTO GENES (GENE_ID,GENE_NAME)" +
                             "VALUES(" + data.get(i).getGeneID() + "," + data.get(i).getGeneName() + ");";
                stmt.executeUpdate(sql); 

                for(j = 0; j < data.get(i).getDataPoints().size(); j++){
                      sql = "INSERT INTO ID (DATA_POINTS)" +
                        "VALUES(" + data.get(j).getDataPoints() + ");"; 
                }

                stmt.close();
                c.commit();
                c.close();
            }
          }
        }catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        
        System.out.println("Inserted Correctly");
    }
}


