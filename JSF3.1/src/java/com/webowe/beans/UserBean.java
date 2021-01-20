/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webowe.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.rowset.CachedRowSet;

@ManagedBean(name="userBean")
@SessionScoped
public class UserBean {
    private final static String DBURL = "jdbc:mysql://localhost:3306/coffee?"
                                        + "useUnicode=true"
                                        + "&useSSL=false"
                                        + "&characterEncoding=latin1"
 					+ "&useJDBCCompliantTimezoneShift=true"
 					+ "&useLegacyDatetimeCode=false"
 					+ "&serverTimezone=UTC";
    private final static String DBUSER = "root";
    private final static String DBPASS = "root";
    private final static String DBDRIVER = "com.mysql.jdbc.Driver";
    
    private String login;
    private String pass;
    private boolean loggedin = false;
    
    private Connection connection;
    private Statement statement;
    private String query;
    private String exceptioninfo;

    public String getExceptioninfo() {
        return exceptioninfo;
    }

    public void setExceptioninfo(String exceptioninfo) {
        this.exceptioninfo = exceptioninfo;
    }

    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }
    
    public void toggleLogin(){
        if (loggedin) loggedin = false;
        else loggedin = true;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public String logout(){
        toggleLogin();
        login = "";
        pass = "";
        return "main";
    }
    
    public String checkUser(){
        setExceptioninfo("");
        query ="SELECT login, pass FROM users";
        ResultSet rowSet;
        try {
            Class.forName(DBDRIVER);
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            PreparedStatement getuser = connection.prepareStatement(query);
            rowSet = getuser.executeQuery();
            
            
            while(rowSet.next()){
                String inlogin = rowSet.getString("LOGIN");
                String inpass = rowSet.getString("PASS");
                
                if (inlogin.equals(login) && inpass.equals(pass)) toggleLogin();
                else setExceptioninfo("Nieprawidłowe dane ");
            }
            getuser.close();
            connection.close();
 	} catch ( ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            setExceptioninfo(e.toString());
 	}

        return "main";
    }
}
