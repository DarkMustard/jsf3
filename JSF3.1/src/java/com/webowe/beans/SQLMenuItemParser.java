/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webowe.beans;

import javax.servlet.http.HttpServletRequest;

public class SQLMenuItemParser {
    public String createSaveQuery(MenuItem  menuItem) {
 	String query = "";
 	query = "INSERT INTO menu (Name,Price,Category) VALUES ('" + menuItem.getName() + "', '" + menuItem.getPrice()
                + "', '" + menuItem.getCategory() +"');";
        
 	return query;
    }
    
    public String createDeleteQuery(MenuItem  menuItem){
        String query = "";
        query = "DELETE FROM menu WHERE ID='" + String.valueOf(menuItem.getId()) + "'";
        
        return query;
    }
    
    public String createUpdateQuery(MenuItem  menuItem){
        String query = "";
        boolean name = false;
        boolean price = false;
        query = "UPDATE menu SET ";
        if (!menuItem.getName().trim().isEmpty()&& menuItem.getName() != null){
            query += "Name = '" + menuItem.getName() + "'";
            name = true;
        }
        if (name) query+=", ";
        if (menuItem.getPrice() != 0.0 && menuItem.getName() != null){
            query += "Price = " + menuItem.getPrice();
            price = true;
        }
        if (price) query+=", ";
        if (!menuItem.getCategory().trim().isEmpty()&& menuItem.getCategory() != null){
            query += "Category = '" + menuItem.getCategory() +"'";
        }
        query += " WHERE ID = " + String.valueOf(menuItem.getId());
        
        return query;
    }
    
    public String createGetItemsQuery(){
        String query = "";
 	query = "SELECT * FROM menu ORDER BY CATEGORY, PRICE" ;
        
 	return query;
    }
    
    public String createSelectbyNameQuery(String name){
        String query = "";
        query = "SELECT * FROM menu WHERE NAME='" + name + "'";
        
        return query;
    }
 	
    public MenuItem parseMenuItemFromRequest(HttpServletRequest request) {
 	String name = request.getParameter("name");
 	double price = Double.parseDouble(request.getParameter("price"));
 	String category = request.getParameter("category");
 		
 	MenuItem menuItem = new MenuItem(name, price, category);
 	return menuItem;
 	}
}
