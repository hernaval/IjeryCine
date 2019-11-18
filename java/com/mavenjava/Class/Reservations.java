/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenjava.Class;

import com.mavenjava.core.Method;
import com.mavenjava.core.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HERNAVAL
 */
public class  Reservations extends Model {
    private String idTemp;
    private String ref;
    private String nomCategorie;
    private int nbPlaces;
    private String numPlace;
    private String status;
    Model m = new Model();
    public Reservations(){
        
    }
    public Reservations(String ref, String numPlace,String nomCategorie ){
        this.ref = ref;
        this.numPlace = numPlace;
        this.nomCategorie = nomCategorie;
        
    }

    public Reservations(String idTemp, String ref) {
        this.idTemp = idTemp;
        this.ref = ref;
    }

    public ResultSet findTemp(){
        String table="Reservations";
        String condition="Ref='"+this.ref+"'  ";
        ResultSet res = super.select(table, condition);
        return res;
        
    }
    public Reservations(String idTemp) {
        this.idTemp = idTemp;
    }
    
    public Reservations(String idTemp, String ref, String nomCategorie, String numPlace) {
        this.idTemp = idTemp;
        this.ref = ref;
        this.nomCategorie = nomCategorie;
       
        this.numPlace = numPlace;
    }
    public ResultSet getPlaces(){
        String table ="Reservations";
        String condition = "IdTemp ='"+this.idTemp+"' and Status ='ok'  ";
        ResultSet res = super.select(table, condition);
        
        return res;
    }
    public ResultSet getInfo(){
        String table ="Reservations";
        String conditions ="Ref ='"+this.ref+"' and IdTemp = '"+this.idTemp+"' ";
        ResultSet res = super.select(table,conditions);
        return res;
    }
    public void checkDone(){
        String table ="Reservations";
        String modif = "Status ='ok' ";
        String condition ="Ref ='"+this.ref+"' and IdTemp = '"+this.idTemp+"' ";
        super.update(table, modif, condition);
    }
    public ResultSet findTicket(){
        String table ="Reservations";
        String conditions = "Ref='"+this.ref+"'  ";
        ResultSet res = super.select(table, conditions);
        return res;
    }
    
    
    public ResultSet getAttendance(){
        String function = "count";
        String field = "NumPlace";
        String name = "total";
        String table ="Reservations";
        String conditions = "group by IdTemp order by total desc limit 4 ";
        ResultSet res = super.calcul(function, field, name, table, conditions);
        
        return res;        
    }
    public void valider(){
        
        String table ="Reservations";
        String column="Ref,IdTemp,NomCategorie,NumPlace";
        String val =" '"+this.ref+"','"+this.idTemp+"', '"+this.nomCategorie+"','"+this.numPlace+"' ";
        m.insertion(table,column,val);
    }
    public ResultSet find(){
        String table ="Reservations";
        String condition = "IdTemp='"+this.idTemp+"'   ";
        ResultSet res = super.select(table,condition);
        return res;
    }
    public void cancel(){
        String table="Reservations";
        String condition=" Ref='"+this.ref+"' ";
        super.delete(table,condition);
    }
    public ArrayList<String> loadPlace(){
        
        ArrayList<String> list = new ArrayList<>();
        try {
            String table="Reservations";
            String condition = " NomCategorie='"+this.nomCategorie+"' and IdTemp='"+this.idTemp+"' ";
            ResultSet res = m.select(table, condition);
            int i=0;   
            while(res.next()){
                list.add(res.getString("NumPlace"));  
            }  
        } catch (SQLException ex) {
            Logger.getLogger(Reservations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ResultSet getResaInfo() {
        String table="Reservations,Temps,Films,Diffusions";
        String conditions ="Reservations.IdTemp = Temps.IdTemp and Diffusions.IdFilm = Films.IdFilm and Diffusions.IdTemp = Reservations.IdTemp   ";
        ResultSet res = super.select(table,conditions);
        return res;
    }
    public ResultSet getTicketInfo(){
        String table ="Reservations";
        String conditions=" ref='"+this.ref+"' ";
        ResultSet res = super.select(table, conditions);
        return res;
    }
    public String getIdTemp() {
        return idTemp;
    }

    public void setIdTemp(String idTemp) {
        this.idTemp = idTemp;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public String getNumPlace() {
        return numPlace;
    }

    public void setNumPlace(String numPlace) {
        this.numPlace = numPlace;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

}
