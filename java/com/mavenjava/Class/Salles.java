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
public class Salles extends Model {
    private String NomSalle;
    
    
    public Salles(){}

    public ResultSet getSalles(){
        String table ="Salles";
        String condition="1=1";
        ResultSet res = super.select(table, condition);
        return res;
    }
    public String getNomSalle() {
        return NomSalle;
    }

    public void setNomSalle(String NomSalle) {
        this.NomSalle = NomSalle;
    }

}
