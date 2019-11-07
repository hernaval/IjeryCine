/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenjava.Class;

import com.mavenjava.core.Model;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author HERNAVAL
 */
public class Films {
    private String idFilm;
    private String titre;
    private String duree;
    private String genre;
    
    public Films(){}

    public String getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(String idFilm) {
        this.idFilm = idFilm;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    
    
    public void insert(){
        String table = "Films";
        String column ="IdFilm,Titre,Duree,Genre";
        String val= " '"+this.idFilm+"','"+this.titre+"','"+this.duree+"','"+this.genre+"' ";
        Model m = new Model();
        m.insertion(table,column,val);
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText("insertion réussi");
        alert.showAndWait();
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
          
}
