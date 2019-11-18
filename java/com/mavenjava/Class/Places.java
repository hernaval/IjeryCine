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
public class Places extends Model {
    public String nomPlace;
    
    public Places(){}
    
    public ResultSet getPlaces(){
        String table ="Places";
        String condition="1=1";
        ResultSet res = super.select(table, condition);
        return res;
    }

    public String getNomPlace() {
        return nomPlace;
    }

    public void setNomPlace(String nomPlace) {
        this.nomPlace = nomPlace;
    }
    
    
}
