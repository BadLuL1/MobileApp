package com.h5200042.hkdtic.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.h5200042.hkdtic.R;
import com.h5200042.hkdtic.model.Products;
import com.h5200042.hkdtic.util.GlideUtil;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsViewHolder> {

    List<Products> products;
    Context context;
    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Products clickProducts);
    }

    public ProductsAdapter(List<Products> products, Context context, OnItemClickListener onItemClickListener) {
        this.products = products;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_products,parent,false);
        ProductsViewHolder productsViewHolder = new ProductsViewHolder(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(products.get(productsViewHolder.getAdapterPosition()));
            }
        });
        return productsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder viewholder, int position) {
        viewholder.txt_product_list_name.setText(products.get(position).getTxtProductListName());
        viewholder.txt_product_price.setText(products.get(position).getTxtProductPrice());

        GlideUtil.downloadimgandshow(context,products.get(position).getImgProductList(),viewholder.img_product_list);


    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
