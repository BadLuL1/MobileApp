package com.h5200042.hkdtic.adaptor;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.h5200042.hkdtic.R;

public class ProductsViewHolder extends RecyclerView.ViewHolder{

    //Anasayfadaki:

    ImageView img_product_list; //ürün fotosu
    TextView txt_product_list_name; // ürün Adı
    TextView txt_product_price; //Ürün fiyatı



    public ProductsViewHolder(@NonNull View itemView) {
        super(itemView);

        img_product_list = itemView.findViewById(R.id.img_product_list);
        txt_product_list_name = itemView.findViewById(R.id.txt_product_list_name);
        txt_product_price = itemView.findViewById(R.id.txt_product_price);

    }
}
