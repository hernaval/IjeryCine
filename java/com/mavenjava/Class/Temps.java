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
public class Temps extends Model {
    private String idTemp;
    private String dateStart;
    private String dateEnd;
    private String timeStart;
    private String timeEnd;
    
   
    public Temps(){
    }

    public Temps(String idTemp) {
        this.idTemp = idTemp;
    }

    public Temps(String idTemp, String dateStart, String timeStart, String timeEnd) {
        this.idTemp = idTemp;
        this.dateStart = dateStart;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public Temps(String dateStart, String timeStart, String timeEnd) {
        this.dateStart = dateStart;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public ResultSet getTemps(){
        String table ="Temps";
        String condition ="1=1";
        ResultSet res = super.select(table,condition);
        return res;
    }
    
   
    
    public ResultSet getInfoTemps(){
        String table ="Temps";
        String condition ="Temps.IdTemp='"+this.idTemp+"'  ";
        ResultSet res = super.select(table,condition);
        return res;
    }
    public void changeTime() {       
        String table="Temps";
        String modif="DateStart = '"+this.dateStart+"',TimeStart='"+this.timeStart+"',"
                + "TimeEnd ='"+this.timeEnd+"' ";
        String condition="IdTemp ='"+this.idTemp+"' ";
        super.update(table, modif, condition);
    }
    
    public void insert(){
        String table ="Temps";
        String column="IdTemp,DateStart,TimeStart,TimeEnd";
        String val=" '"+this.idTemp+"','"+this.dateStart+"','"+this.timeStart+"','"+this.timeEnd+"'  ";
        super.insertion(table, column, val);
    }

    
    
    public String getIdTemp() {
        return idTemp;
    }

    public void setIdTemp(String idTemp) {
        this.idTemp = idTemp;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    
    
    
    
    
}
