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
import com.h5200042.hkdtic.model.AdressModel;
import com.h5200042.hkdtic.pages.CreditCard;
import com.h5200042.hkdtic.pages.DetailScreen;

import java.util.ArrayList;

public class AdressAdapter extends RecyclerView.Adapter<AdressAdapter.ModelsViewHolder> {

    Context context;

    ArrayList<AdressModel> adressModel;

    FirebaseFirestore firestore;
    FirebaseAuth auth;



    public AdressAdapter(Context context, ArrayList<AdressModel> adressModel) {
        this.context = context;
        this.adressModel = adressModel;

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ModelsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_of_adress,parent,false);
        return new ModelsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelsViewHolder holder, int position) {
        AdressModel model = adressModel.get(position);

        holder.adresName.setText(model.getProfileName());
        holder.adresNameProfile.setText(model.getName());
        holder.adresPhone.setText(model.getPhone());
        holder.adresAdres.setText(model.getAdres());
        holder.adresSurnameProfile.setText(model.getSurname());

        holder.trashBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore.collection("Users").document(auth.getCurrentUser().getUid())
                        .collection("Adres")
                        .document(adressModel.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    adressModel.remove(adressModel.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Adres silindi.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Adres silinemedi!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CreditCard.class);
                intent.putExtra("adres",adressModel.get(position)); //detail
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return adressModel.size();
    }


    public class ModelsViewHolder extends RecyclerView.ViewHolder {

        TextView adresName, adresNameProfile, adresSurnameProfile, adresPhone, adresAdres;
        ImageView trashBox;


        public ModelsViewHolder(@NonNull View itemView) {
            super(itemView);

            adresName = itemView.findViewById(R.id.adresName);
            adresNameProfile = itemView.findViewById(R.id.adresNameProfile);
            adresSurnameProfile = itemView.findViewById(R.id.adresSurnameProfile);
            adresPhone = itemView.findViewById(R.id.adresPhone);
            adresAdres = itemView.findViewById(R.id.adresAdres);
            trashBox = itemView.findViewById(R.id.img_trashAdres);



        }
    }
}
