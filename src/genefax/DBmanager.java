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
    private String conn = "jdbc:sqlite:GeneFax.db";
    DBmanager(String conn){
        this.conn = conn;
    }
    
    public void createTable(){
        Connection c = null;
        Statement stmt = null;
        Statement stmt2 = null;
        Statement stmt3 = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:GeneFax.db");
            System.out.println("[DEBUG: create]Opened database successfully");

            stmt = c.createStatement();
            String genes = "CREATE TABLE GENES" +
                           "(GENE_ID TEXT PRIMARY KEY," +
                           "GENE_NAME TEXT NOT NULL," +
                           "FILENAME VARCHAR(100) NOT NULL," +
                           "DATA_LABEL TEXT," + 
                           "DATA_AVG FLOAT(2));";
            System.out.println("[DEBUG: genes]"+genes);
            stmt.executeUpdate(genes);
             
            String geneID = "CREATE TABLE DATAPOINTS" +
                            "(AUTO_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "DATA_POINT FLOAT(4)," +
                            "FILENAME VARCHAR(100) REFERENCES GENES(FILENAME)," +
                            "GENE_ID VARCHAR(20) REFERENCES GENES(GENE_ID));";
            stmt.executeUpdate(geneID);
             
            String geneFold = "CREATE TABLE FOLD" +
                              "(FOLDS FLOAT(2)," +
                              "GENE_ID PRIMARY KEY REFERENCES GENES(GENE_ID));";
            stmt.executeUpdate(geneFold);
            
            stmt.close(); 
            c.close();
             
            } catch ( Exception e ) {
             System.err.println( e.getClass().getName() + ": " + e.getMessage() );
             System.exit(0);
            };
        System.out.println("[DEBUG: create]Table created successfully");
    }
    
    public boolean dropTable( String con ){
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection(con);
          System.out.println("[DEBUG: DROP] Opened database successfully");

          stmt = c.createStatement();
          
          String sql = "DROP TABLE GeneFax.GENES"; 
          stmt.executeUpdate(sql);
          sql = "DROP TABLE GeneFax.DATAPOINTS"; 
          stmt.executeUpdate(sql);
          sql = "DROP TABLE GeneFax.FOLD"; 
          stmt.executeUpdate(sql);
          
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          return false;
        }   
        return true;
    }// end dropTable;
    
    public boolean insert( ArrayList<GeneDataRow> gdr, String filename ){
        Connection c = null;
        Statement stmt = null;
        System.out.println("[DEBUG: INSERT]starting");
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:GeneFax.db");
            c.setAutoCommit(false);
            System.out.println("[DEBUG: INSERT]Opened database successfully");
            String sql;
            stmt = c.createStatement();
            
            for(int i = 0; i < gdr.size(); i++){
                sql = "INSERT INTO GENES (GENE_ID,GENE_NAME,FILENAME,DATA_LABEL,DATA_AVG)" +
                        "VALUES ( \""+ gdr.get(i).getGeneID() + "\",\""+
                        gdr.get(i).getGeneName() + "\",\""+ 
                        filename + "\",\""+ 
                        gdr.get(i).getDataLabel() + "\","+ 
                        gdr.get(i).getDataAvgStr() +" );";
                System.out.println("[DEBUG: insert sql]" + sql);
                stmt.executeUpdate(sql);
                
                for(int j = 0; j < gdr.get(i).getDataPoints().size(); j++){
                    sql = "INSERT INTO DATAPOINTS( DATA_POINT,FILENAME,GENE_ID ) "+
                            "VALUES ( "+gdr.get(i).getDataPoints().get(j)+
                            ",\"" + filename +
                            "\", \"" + gdr.get(i).getGeneID() + "\" );";
//                    System.out.println("[DEBUG: insert sub sql]" + sql);
                    stmt.executeUpdate(sql);
                }// end inner for
            }// end outer for
            stmt.close();
            c.commit();
            c.close();
        } catch( Exception e ){
            e.printStackTrace();
            return false;
        }
        return true;
    }// end insert2
    
    /**
     * 
     * @param filename name of data file you want to poll
     * @return arraylist of genedatarows, same as was originally entered into the db
     */
    public ArrayList<GeneDataRow> getAllFromFile( String filename ){
        ArrayList<GeneDataRow> ret = new ArrayList<GeneDataRow>(128);
        
        Connection c = null;
        Statement stmt = null;
        Statement sub_stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:GeneFax.db");
            c.setAutoCommit(true);
            System.out.println("[DEBUG: get file]Opened database successfully");

            stmt = c.createStatement();
            String sql0 = "SELECT * FROM GENES WHERE FILENAME LIKE \""+filename+"\";";
            System.out.println("[DEBUG: get 0]"+sql0);
            ResultSet rs = stmt.executeQuery( sql0 );
//            c.commit();
            while ( rs.next() ) {
                System.out.println("check");    
                GeneDataRow gdr = new GeneDataRow(
                    rs.getString("GENE_NAME"), 
                    rs.getString("GENE_ID"),
                    rs.getString("DATA_LABEL"),
                    rs.getFloat("DATA_AVG")
                );
                
                sub_stmt = c.createStatement();
                ResultSet sub_rs = sub_stmt.executeQuery( 
                        "SELECT * FROM DATAPOINTS "+
                        "WHERE FILENAME LIKE \""+filename+
                        "\" AND GENE_ID LIKE \""+ rs.getString("GENE_ID") +"\";");
//                c.commit();
                while( sub_rs.next() ){
                    gdr.addDataPoint( sub_rs.getFloat("DATA_POINT") );
                }// end inner while
                
                System.out.println("[DEBUG: get check]"+
                        gdr.getDataLabel()+", "+
                        gdr.getGeneID()+", "+
                        gdr.getGeneName()+", "+
                        gdr.getDataPoints().get(0)+","+
                        gdr.getDataPoints().get(1));
                
                ret.add(gdr);
                
                sub_rs.close();
            }// end outer while
            
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("[DEBUG: get file]Operation done successfully");
        
        return ret;
    }// end getAllFromFile
    
}// end DBmanager


