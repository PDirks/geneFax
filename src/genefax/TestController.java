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
public class TestController {
    
    public TestController(){}
    
    public boolean runTests(){
        
        return testCSVload();
    
    }
    
    public boolean testCSVload(){
        String path = "/home/pete/Downloads/test_data_0.csv";
        int replicants = 2;
        ImportManager im = new ImportManager(path, replicants);
        ArrayList<GeneDataRow> gdrs = im.importCSV();
        
        boolean ret;
        ret = gdrs.get(0).getGeneName().equals("Emp1");
        ret = gdrs.get(0).getGeneID().equals("NM_010128");
        ret = gdrs.get(0).getDataPoints().get(0).equals(2057);
        ret = gdrs.get(0).getDataPoints().get(1).equals(2334);
        
        if( true == ret ) System.out.print("CSV case 0 pass");
        
        ret = gdrs.get(3).getGeneName().equals("Dctd");
        ret = gdrs.get(3).getGeneID().equals("NM_001161516");
        ret = gdrs.get(3).getDataPoints().get(0).equals(72);
        ret = gdrs.get(3).getDataPoints().get(1).equals(56);
        
        if( true == ret ) System.out.print("CSV case 1 pass");
        
        ret = gdrs.get(5).getGeneName().equals("Dctd");
        ret = gdrs.get(5).getGeneID().equals("NR_027759");
        ret = gdrs.get(5).getDataPoints().get(0).equals(80);
        ret = gdrs.get(5).getDataPoints().get(1).equals(65);
        
        if( true == ret ) System.out.print("CSV case 2 pass");
        
        ret = gdrs.get(6).getGeneName().equals("Ifnb1");
        ret = gdrs.get(6).getGeneID().equals("NM_010510");
        ret = gdrs.get(6).getDataPoints().get(0).equals(559);
        ret = gdrs.get(6).getDataPoints().get(1).equals(406);
        
        if( true == ret ) System.out.print("CSV case 3 pass");
        
        return ret;
        
    }
    
}
