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
public class Admins extends Model {
    private String username;
    private String password;

    public Admins(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ResultSet auth(){
        String table = "Admins";
        String conditions ="Username ='"+this.username+"' and Password='"+this.password+"' ";
        ResultSet res = super.select(table,conditions);
        return res;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
