/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenjava.Class;

import com.calendarfx.model.CalendarEvent;
import com.mavenjava.core.Model;
import java.sql.ResultSet;
import javafx.event.EventHandler;

/**
 *
 * @author HERNAVAL
 */
public class Diffusions extends Model {
    private String idFilm;
    private String idTemp;
    private String nomSalle;
  
    public Diffusions(){}

    public Diffusions(String idTemp) {
        this.idTemp = idTemp;
    }

    public ResultSet getRepart(){
        String function = "count";
        String fields = "IdFilm";
        String name ="total";
        String table ="Diffusions";
        String condition ="NomSalle ='"+this.nomSalle+"' ";
        ResultSet res =super.calcul(function, fields, name, table, condition);
        return res;
    }
    
    public ResultSet getLive(String param){
        String table = "Diffusions,Temps,Films";
        String conditions ="Films.IdFilm = Diffusions.IdFilm and Diffusions.IdTemp = Temps.IdTemp and Temps.DateStart='"+param+"'  ";
        ResultSet res = super.select(table, conditions);
        
        return res;
                
    }
    public Diffusions(String idFilm, String idTemp, String nomSalle) {
        this.idFilm = idFilm;
        this.idTemp = idTemp;
        this.nomSalle = nomSalle;
    }

    public Diffusions(String idTemp, String nomSalle) {
        this.idTemp = idTemp;
        this.nomSalle = nomSalle;
    }
    
    public ResultSet getTitre(){
        String tables ="Diffusions,Films";
        String conditions = "Diffusions.IdFilm  = Films.IdFilm and Diffusions.IdTemp ='"+this.idTemp+"'  ";
        ResultSet res= super.select(tables, conditions);
        return res;
    }
    
    public ResultSet getProgram(String param){
        String table ="Diffusions,Temps,Films";
        String conditions = " Diffusions.IdTemp =Temps.IdTemp and"
                + " Diffusions.IdFilm = Films.IdFilm and Diffusions.NomSalle = '"+param+"' ";
        ResultSet res = super.select(table, conditions);
        return res;
    }
    
    public ResultSet checkLibre(String date,String time){
        String table = "Diffusions,Temps";
        String condition="Diffusions.IdTemp=Temps.IdTemp and Temps.DateStart ='"+date+"' "
                + "and Temps.TimeStart='"+time+"' and Diffusions.NomSalle='"+this.nomSalle+"' ";
        ResultSet res = super.select(table, condition);
        return res;
    }
    
    public void insert(){
        String table = "Diffusions";
        String column="IdFilm,IdTemp,NomSalle";
        String val=" '"+this.idFilm+"','"+this.idTemp+"','"+this.nomSalle+"' ";
        super.insertion(table, column, val);
    }
    public String getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(String idFilm) {
        this.idFilm = idFilm;
    }

    public String getIdTemp() {
        return idTemp;
    }

    public void setIdTemp(String idTemp) {
        this.idTemp = idTemp;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    
    
}
