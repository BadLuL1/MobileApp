package com.h5200042.hkdtic.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Products {
    @SerializedName("img_product_list")
    @Expose
    private String imgProductList;
    @SerializedName("txt_product_list_name")
    @Expose
    private String txtProductListName;
    @SerializedName("txt_product_price")
    @Expose
    private String txtProductPrice;

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

}
