/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mavenjava.core;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HERNAVAL
 */
public class Model {
    private Connection con;
    private Statement stat;
    
   public Model(){
        try {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/cinema","root","navalona");
            stat = (Statement) con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   public Connection etablirConnection(){
       return con;
   }
   
   public Connection insertion(String table,String column,String val){
        try {
            String sql = "insert into "+table+"("+column+")values("+val+")";
            stat.executeUpdate(sql);
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
   }
   
   public ResultSet select(String table,String condition){
        ResultSet res = null;
        try {
            String sql = "select * from "+table+" where "+condition;
           res  = stat.executeQuery(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
   }
   
   public Connection update(String table,String modif,String  condition){
       String sql = "update "+table+" set "+modif+" where "+condition;     
        try {
            stat.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
       return con;
   }
   
   public ResultSet calcul(String function,String fields,String name,String table,String condition){ 
       ResultSet res=null;
        try {
            String sql = "select "+function+"("+fields+")as "+name+" from "+table+" where  "+condition;
            res = stat.executeQuery(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
   }
   
   public Connection delete(String table,String condition){
        try {
            String sql = "delete from "+table+" where "+condition;
            stat.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
   }
   
   
   
}
