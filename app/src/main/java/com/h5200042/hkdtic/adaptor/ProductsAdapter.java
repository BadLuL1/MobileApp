package com.h5200042.hkdtic.adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.h5200042.hkdtic.R;
import com.h5200042.hkdtic.model.Products;
import com.h5200042.hkdtic.pages.DetailScreen;
import com.h5200042.hkdtic.util.GlideUtil;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    Context context;

    ArrayList<Products> list;

    public ProductsAdapter(Context context, ArrayList<Products> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_of_products,parent,false);
        return new ProductsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {

        Products products = list.get(position);
        holder.txt_product_price.setText(products.getTxtProductPrice());
        holder.txt_product_list_name.setText(products.getTxtProductListName());
        //holder.txt_sellerName.setText(products.getTxtSellerName());
        //holder.txt_explanation.setText(products.getTxtExplanation());

        //home sınıfındaki şeyleri yapıcam sadece detail için farklı sınıf açıcam.


        Glide.with(context).load(list.get(position).getImgProductList()).into(holder.img_product_list);


        //Itemin üzerine tıklanıldığında itemin bilgi ekranına yönlendiriliyor.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailScreen.class);
                intent.putExtra("detail",list.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ProductsViewHolder extends RecyclerView.ViewHolder{

        TextView txt_product_list_name, txt_product_price;
        ImageView img_product_list;

        public ProductsViewHolder(@NonNull View itemView){
            super(itemView);

            img_product_list = itemView.findViewById(R.id.img_product_list);  //E1
            txt_product_list_name = itemView.findViewById(R.id.txt_product_list_name);  //E1
            txt_product_price = itemView.findViewById(R.id.txt_product_price);  //E1


        }



    }

}
