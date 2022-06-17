package com.h5200042.hkdtic.adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.h5200042.hkdtic.R;
import com.h5200042.hkdtic.model.CartModel;
import com.h5200042.hkdtic.model.CreditCardModel;
import com.h5200042.hkdtic.pages.ConfirmOrder;
import com.h5200042.hkdtic.pages.CreditCard;

import java.util.ArrayList;

public class CreditCardAdapter extends RecyclerView.Adapter<CreditCardAdapter.ModelsViewHolder> {

    Context context;

    ArrayList<CreditCardModel> cardModelsList;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public CreditCardAdapter(Context context, ArrayList<CreditCardModel> cardModelsList) {
        this.context = context;
        this.cardModelsList = cardModelsList;

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

    }

    @NonNull
    @Override
    public ModelsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_of_credit_card,parent,false);
        return new CreditCardAdapter.ModelsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelsViewHolder holder, int position) {


        //

        CreditCardModel model = cardModelsList.get(position);

        holder.cardOwnerName.setText(model.getCardOwnerName());
        holder.cardNumber.setText(model.getCardNumber());
        holder.cardExpiredMonth.setText(model.getCardExpirationDateMonth());
        holder.cardExpiredYear.setText(model.getGetCardExpirationDateYear());

        holder.trashBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore.collection("Users").document(auth.getCurrentUser().getUid())
                        .collection("CreditCard")
                        .document(cardModelsList.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    cardModelsList.remove(cardModelsList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Kartınız silinmiştir.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Kartınız silinemedi!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ConfirmOrder.class);

                intent.putExtra("kart",cardModelsList.get(position));
                //BURALAR SİPARİŞ ONAYLAMA EKRANINA BİLGİYİ GÖNDERMEK İÇİN.
                intent.putExtra("cardOwner",cardModelsList.get(position).getCardOwnerName());
                intent.putExtra("cardNumber",cardModelsList.get(position).getCardNumber());
                //
                context.startActivity(intent);

            }
        });




    }

    @Override
    public int getItemCount() { return cardModelsList.size(); }


    public class ModelsViewHolder extends RecyclerView.ViewHolder {

        TextView cardOwnerName, cardNumber, cardExpiredMonth, cardExpiredYear;
        ImageView trashBox;

        public ModelsViewHolder(@NonNull View itemView) {
            super(itemView);

            cardOwnerName = itemView.findViewById(R.id.txt_cardOwnerName);
            cardNumber = itemView.findViewById(R.id.txt_cardNumber);
            cardExpiredMonth = itemView.findViewById(R.id.txt_cardLastExpiredMonth);
            cardExpiredYear = itemView.findViewById(R.id.txt_cardLastExpiredYear);
            trashBox =itemView.findViewById(R.id.img_trashCard);

        }
    }
}