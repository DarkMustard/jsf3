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
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Marcin
 */
public class MenuItemDAO {
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
    
    private Connection connection;
    private Statement statement;
    private String query;
    private SQLMenuItemParser sqlMenuItemParser;
    private String exceptioninfo = "";
    
    public MenuItemDAO() {
 	sqlMenuItemParser = new SQLMenuItemParser();
    }

    public String getExceptioninfo() {
        return exceptioninfo;
    }

    public void setExceptioninfo(String exceptioninfo) {
        this.exceptioninfo = exceptioninfo;
    }
    
    public void save(MenuItem menuItem) {
 	query = sqlMenuItemParser.createSaveQuery(menuItem);
        setExceptioninfo(query);
 	try {
            Class.forName(DBDRIVER);
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            statement = connection.createStatement();
            statement.executeUpdate(query);
 
            statement.close();
            connection.close();
 	} catch ( ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            setExceptioninfo(e.toString());
 	}
    }
    
    public void delete(MenuItem menuItem) {
 	query = sqlMenuItemParser.createDeleteQuery(menuItem);
 
 	try {
            Class.forName(DBDRIVER);
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            statement = connection.createStatement();
            statement.executeUpdate(query);
 
            statement.close();
            connection.close();
 	} catch ( ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            setExceptioninfo(e.toString());
 	}
    }
    
    public void update(MenuItem menuItem) {
 	query = sqlMenuItemParser.createUpdateQuery(menuItem);
        //setExceptioninfo(query);
 	try {
            Class.forName(DBDRIVER);
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            statement = connection.createStatement();
            statement.executeUpdate(query);
 
            statement.close();
            connection.close();
 	} catch ( ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            //setExceptioninfo(e.toString());
 	}
    }
    
    public ResultSet getAllMenuItems() throws SQLException {
        query = sqlMenuItemParser.createGetItemsQuery();
        CachedRowSet rowSet;
        try
        {
            Class.forName(DBDRIVER);
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            PreparedStatement getAddresses = connection.prepareStatement(query);
         
            rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate( getAddresses.executeQuery() );
            getAddresses.close();
            connection.close();
         
        }catch ( ClassNotFoundException | SQLException e) {
            exceptioninfo = e.toString();
            rowSet = new com.sun.rowset.CachedRowSetImpl();
 	}
        
        return rowSet; 
   }
    
    public ResultSet selectByName(String name) throws SQLException{
        query = sqlMenuItemParser.createSelectbyNameQuery(name);
        CachedRowSet rowSet;
        try
        {
            Class.forName(DBDRIVER);
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            PreparedStatement getAddresses = connection.prepareStatement(query);
         
            rowSet = new com.sun.rowset.CachedRowSetImpl();
            rowSet.populate( getAddresses.executeQuery() );
            getAddresses.close();
            connection.close();
         
        }catch ( ClassNotFoundException | SQLException e) {
            exceptioninfo = e.toString();
            rowSet = new com.sun.rowset.CachedRowSetImpl();
 	}
        
        return rowSet;
    }
}
