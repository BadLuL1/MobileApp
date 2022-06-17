package com.h5200042.hkdtic.adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
import com.h5200042.hkdtic.pages.DetailScreen;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;
    List<CartModel> cartModelList;
    FirebaseFirestore firestore;
    FirebaseAuth auth;


    public CartAdapter(Context context, List<CartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_of_cart_products,parent,false);
        return new ViewHolder(v);

        //return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_cart_products,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productsName.setText(cartModelList.get(position).getProductName());
        holder.sellerName.setText(cartModelList.get(position).getSellerName());
        holder.quantity.setText(String.valueOf(cartModelList.get(position).getQuantity()));
        holder.totalProductPrice.setText(String.valueOf(cartModelList.get(position).getProductPrice()));

        Glide.with(context).load(cartModelList.get(position).getProductPhotoURL()).into(holder.productPhoto);





        holder.trashBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                        .collection("CurrentUser")
                        .document(cartModelList.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful()){
                                    cartModelList.remove(cartModelList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Ürün silindi", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Hata, ürün silinemedi"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });





    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView sellerName,quantity,productsName,totalProductPrice;
        ImageView productPhoto, trashBox, addItem, removeItem;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sellerName = itemView.findViewById(R.id.txt_sellerName);
            quantity = itemView.findViewById(R.id.txt_quantity);
            productsName = itemView.findViewById(R.id.txt_cart_product_name);
            totalProductPrice = itemView.findViewById(R.id.txt_product_total_price);
            productPhoto = itemView.findViewById(R.id.img_cart_product);
            trashBox = itemView.findViewById(R.id.trashBox);
            addItem = itemView.findViewById(R.id.plusCircle);
            removeItem = itemView.findViewById(R.id.minusCircle);




        }
    }
}
