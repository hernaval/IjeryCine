/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenjava.Class;

import com.mavenjava.core.Model;
import java.sql.ResultSet;

/**
 *
 * @author HERNAVAL
 */
public class Tarifs extends Model {
    private String nomCategorie;
    private String idTemp;
    private float prix;

    
    public Tarifs(String nomCategorie, String idTemp, float prix) {
        this.nomCategorie = nomCategorie;
        this.idTemp = idTemp;
        this.prix = prix;
    }

    public Tarifs(String idTemp,String nomCategorie) {
        this.idTemp = idTemp;
        this.nomCategorie = nomCategorie;
    }
    
    public void insert(){
        String table="Tarifs";
        String column="NomCategorie,IdTemp,Prix";
        String val=" '"+this.nomCategorie+"','"+this.idTemp+"','"+this.prix+"' ";
        super.insertion(table, column, val);
    }

    public ResultSet getTarifs(){
        String table = "Tarifs";
        String conditions = "Tarifs.IdTemp = '"+this.idTemp+"' and Tarifs.NomCategorie = '"+this.nomCategorie+"'  ";
        ResultSet res = super.select(table, conditions);
        return res;
    }
    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getIdTemp() {
        return idTemp;
    }

    public void setIdTemp(String idTemp) {
        this.idTemp = idTemp;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    
}
