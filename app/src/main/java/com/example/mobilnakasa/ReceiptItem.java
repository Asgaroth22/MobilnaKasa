package com.example.mobilnakasa;

import java.util.ArrayList;
import java.util.Date;

public class ReceiptItem {
    private ArrayList<ProductItem> products;
    String id;
    Date date;
    Double totalPrice;

    public ReceiptItem() {
    }

    public ArrayList<ProductItem> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductItem> products) {
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
