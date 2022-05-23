package com.h5200042.hkdtic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Products implements Serializable {

    public String imgProductList;
    public String txtProductListName;
    public String txtProductPrice;
    //********
    public String txtExplanation;
    public String txtSellerName;


    public Products(){

    }

    public Products(String imgProductList, String txtProductListName, String txtProductPrice, String txtExplanation, String txtSellerName) {
        this.imgProductList = imgProductList;
        this.txtProductListName = txtProductListName;
        this.txtProductPrice = txtProductPrice;
        this.txtExplanation = txtExplanation;
        this.txtSellerName = txtSellerName;
    }

    public String getImgProductList() {
        return imgProductList;
    }

    public void setImgProductList(String imgProductList) {
        this.imgProductList = imgProductList;
    }

    public String getTxtProductListName() {
        return txtProductListName;
    }

    public void setTxtProductListName(String txtProductListName) {
        this.txtProductListName = txtProductListName;
    }

    public String getTxtProductPrice() {
        return txtProductPrice;
    }

    public void setTxtProductPrice(String txtProductPrice) {
        this.txtProductPrice = txtProductPrice;
    }

    public String getTxtExplanation() {
        return txtExplanation;
    }

    public void setTxtExplanation(String txtExplanation) {
        this.txtExplanation = txtExplanation;
    }

    public String getTxtSellerName() {
        return txtSellerName;
    }

    public void setTxtSellerName(String txtSellerName) {
        this.txtSellerName = txtSellerName;
    }
}
