/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenjava.Class;

import com.mavenjava.core.Model;

/**
 *
 * @author HERNAVAL
 */
public class Disponibilites {
    private String idTemp;
    private String nomCategorie;
    private String nomSalle;
    private int placeDispo;
 
    public Disponibilites(){}

    public Disponibilites(String idTemp, String nomCategorie) {
        this.idTemp = idTemp;
        this.nomCategorie = nomCategorie;
    }

    
    public void majNbPlace(int nb){
        Model m = new Model();
        String table ="Disponibilites";
        String modif="PlaceDispo = PlaceDispo - '"+nb+"' ";
        String condition =" IdTemp = '"+this.idTemp+"' and NomCategorie = '"+this.nomCategorie+"'  ";
        m.update(table,modif,condition);
    }

    public String getIdTemp() {
        return idTemp;
    }

    public void setIdTemp(String idTemp) {
        this.idTemp = idTemp;
    }

    public int getPlaceDispo() {
        return placeDispo;
    }

    public void setPlaceDispo(int placeDispo) {
        this.placeDispo = placeDispo;
    }
    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    
}
