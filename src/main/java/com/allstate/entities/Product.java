package com.allstate.entities;


import lombok.Data;

import javax.persistence.*;

import static com.sun.xml.internal.ws.policy.sourcemodel.wspolicy.XmlToken.Name;

@Entity
@Table(name = "product")
@Data
public class Product {

    private int id;
    private String name;
    private String stocknum;
    private int rating;
    private int reviews;
    private int discount;
    private int actualprice;
    private int listprice;
    private int quantity;
    private boolean restricted;
    private String description;
    private int version;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    public int getReviews() {
        return rating;
    }

    public void setReviews(int rating) {
        this.rating = rating;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){ this.quantity = quantity;}

    public boolean isRestricted(){ return restricted;}
    public void setRestricted(boolean restricted){this.restricted = restricted;}

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }
    public void setId(int id) {this.id = id;}

    @Version
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    public int getdiscount() {
        return discount;
    }
    public void setdiscount(int discount) {
        this.discount = discount;
        this.actualprice = this.listprice - ((listprice * discount) /100);
    }

    @Column(nullable = false)
    public int getListprice() {
        return listprice;
    }
    public void setListprice(int listprice) {
        this.listprice = listprice;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    @Column(nullable = false)
    public String getStocknum() {
        return stocknum;
    }
    public void setStocknum(String stocknum) {
        this.stocknum = stocknum;
    }






}
