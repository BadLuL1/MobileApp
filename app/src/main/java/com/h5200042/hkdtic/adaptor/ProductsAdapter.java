package com.h5200042.hkdtic.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.h5200042.hkdtic.R;
import com.h5200042.hkdtic.model.Products;
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

        GlideUtil.downloadimgandshow(context,products.getImgProductList(),holder.img_product_list);

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

            img_product_list = itemView.findViewById(R.id.img_product_list);
            txt_product_list_name = itemView.findViewById(R.id.txt_product_list_name);
            txt_product_price = itemView.findViewById(R.id.txt_product_price);


        }



    }

}
