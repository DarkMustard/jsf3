/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webowe.beans;


public class MenuItem{
    private int id;
    private String name;
    private double price;
    private String category;
    
    public MenuItem(int id, String name, double price, String category){
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }
    
    public MenuItem(String name, double price, String category){
        this.name = name;
        this.price = price;
        this.category = category;
    }
    
    public MenuItem(String name, String price, String category){
        this.name = name;
        setPrice(price);
        this.category = category;
    }
    
    public MenuItem(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

}
