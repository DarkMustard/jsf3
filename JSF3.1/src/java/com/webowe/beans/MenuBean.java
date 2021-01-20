/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webowe.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

@ManagedBean(name="menuBean")
public class MenuBean implements Serializable{
    private String id;
    private String name;
    private double price;
    private String category;
    private String exceptioninfo;
    private String selectquery;
    
    public String getSelectquery() {
        return selectquery;
    }

    public void setSelectquery(String selectquery) {
        this.selectquery = selectquery;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return String.valueOf(price);
    }

    public void setPrice(String price) {
        this.price = Double.parseDouble(price);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExceptioninfo() {
        return exceptioninfo;
    }

    public void setExceptioninfo(String exceptioninfo) {
        this.exceptioninfo = exceptioninfo;
    }
    
    public String save(){
        MenuItemDAO dao = new MenuItemDAO();
        MenuItem menuItem = new MenuItem(name, price, category);
        dao.save(menuItem);
        //setExceptioninfo(dao.getExceptioninfo());
        
        return "index";
    }
    
    public String delete(){
        MenuItemDAO dao = new MenuItemDAO();
        /*name = "";
        price = 0.0;
        category = "";*/
        MenuItem menuItem = new MenuItem(Integer.parseInt(id), name, price, category);
        dao.delete(menuItem);
        //setExceptioninfo(dao.getExceptioninfo());
        
        return "index";
    }
    
    public String update(){
        MenuItemDAO dao = new MenuItemDAO();
        /*name = "";
        price = 0.0;
        category = "";*/
        MenuItem menuItem = new MenuItem(Integer.parseInt(id), name, price, category);
        dao.update(menuItem);
        setExceptioninfo(dao.getExceptioninfo());
        
        return "index";
    }
    
    public ResultSet getMenuitems() throws SQLException{
        MenuItemDAO dao = new MenuItemDAO();
        ResultSet rowSet;
        try{
            rowSet = dao.getAllMenuItems();
            //setExceptioninfo(dao.getExceptioninfo());   
        }catch ( SQLException e){
            rowSet = new com.sun.rowset.CachedRowSetImpl();
            //setExceptioninfo(e.toString());
        }
        return rowSet;
    }
    
    public ResultSet getSelect() throws SQLException{
        MenuItemDAO dao = new MenuItemDAO();
        ResultSet rowSet;
        if(selectquery == "" || selectquery == null){
            return getMenuitems();
        }
        try{
            rowSet = dao.selectByName(selectquery);
            //setExceptioninfo(dao.getExceptioninfo());   
        }catch ( SQLException e){
            rowSet = new com.sun.rowset.CachedRowSetImpl();
            setExceptioninfo(e.toString());
        }
        //setExceptioninfo(dao.getExceptioninfo());
        return rowSet;
    }
    
    public String clearSelection(){
        selectquery = "";
        return "index";
    }
}
