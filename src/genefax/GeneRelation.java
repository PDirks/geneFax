/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genefax;

/**
 *
 * @author pete
 */
public class GeneRelation {
    private String GeneName;
    private String GeneID;
    private float FoldChange;
    private float QValue;
    
    private String relation_a;
    private String relation_b;
    
    public GeneRelation( String name, String id, float fc, float q ){
        this.GeneName = name;
        this.GeneID = id;
        this.FoldChange = fc;
        this.QValue = q;
    }
    
    public void setRelation(String relation_a, String relation_b){
        this.relation_a = relation_a;
        this.relation_b = relation_b;
    }
    
    public String getGeneName(){
        return this.GeneName;
    }
    
    public String getGeneID(){
        return this.GeneID;
    }
    
    public float getFoldChange(){
        return this.FoldChange;
    }
    
    public float getQValue(){
        return this.QValue;
    }
    
}
