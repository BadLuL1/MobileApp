package com.h5200042.hkdtic.model;

import com.h5200042.hkdtic.adaptor.CartAdapter;

public class CartModel {
    String currentDate;
    String currentTime;
    String productName;
    String productPhotoURL;
    String productPrice;
    int quantity;
    String sellerName;
    int totalPrice;
    String documentId;


    public CartModel(){

    }

    public CartModel(String currentDate, String currentTime, String productName, String productPhotoURL, String productPrice, int quantity, String sellerName, int totalPrice) {
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.productName = productName;
        this.productPhotoURL = productPhotoURL;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.sellerName = sellerName;
        this.totalPrice = totalPrice;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPhotoURL() {
        return productPhotoURL;
    }

    public void setProductPhotoURL(String productPhotoURL) {
        this.productPhotoURL = productPhotoURL;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
