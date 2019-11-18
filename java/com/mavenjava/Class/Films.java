/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenjava.Class;

import com.mavenjava.core.Model;
import java.sql.ResultSet;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author HERNAVAL
 */
public class Films extends Model {
    private String idFilm;
    private String titre;
    private String duree;
    private String genre;
    
    public Films(){}

    public Films(String idFilm, String titre, String duree, String genre) {
        this.idFilm = idFilm;
        this.titre = titre;
        this.duree = duree;
        this.genre = genre;
    }
    public ResultSet count(){
        String function ="count";
        String field = "IdFilm";
        String name="total";
        String table="Films";
        String condition ="1=1";
        ResultSet res = super.calcul(function, field, name, table, condition);
        return res;
    }

    public Films(String titre) {
        this.titre = titre;
    }
    
    public void delete(){
        String table ="Films";
        String condition ="Titre ='"+this.titre+"'  ";
        super.delete(table, condition);
    }
    
    public ResultSet getFilms(){
        String table ="Films";
        String condition="1=1";
        ResultSet res = super.select(table, condition);
        return res;
    }
    public ResultSet getSelected(){
        String table ="Films";
        String condition="Titre ='"+this.titre+"' ";
        ResultSet res = super.select(table, condition);
        return res;
    }
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
