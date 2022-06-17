package com.h5200042.hkdtic.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.h5200042.hkdtic.R;
import com.h5200042.hkdtic.model.CartModel;

import java.util.List;

public class ConfirmOrderProductsAdapter extends RecyclerView.Adapter<ConfirmOrderProductsAdapter.ViewHolder> {

    Context context;
    List<CartModel> cartModelList;
    FirebaseFirestore firestore;
    FirebaseAuth auth;


    public ConfirmOrderProductsAdapter(Context context, List<CartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_of_products_confirm_order,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.productsName.setText(cartModelList.get(position).getProductName());
        holder.quantity.setText(String.valueOf(cartModelList.get(position).getQuantity()));
        holder.totalProductPrice.setText(String.valueOf(cartModelList.get(position).getProductPrice()));

        Glide.with(context).load(cartModelList.get(position).getProductPhotoURL()).into(holder.productPhoto);

    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView quantity,productsName,totalProductPrice;
        ImageView productPhoto;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            quantity = itemView.findViewById(R.id.textView33);
            productsName = itemView.findViewById(R.id.textView31);
            totalProductPrice = itemView.findViewById(R.id.txt_product_total_price);
            productPhoto = itemView.findViewById(R.id.imageView8);

        }
    }
}
