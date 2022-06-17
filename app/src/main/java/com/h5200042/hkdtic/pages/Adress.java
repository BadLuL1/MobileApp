package com.h5200042.hkdtic.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.h5200042.hkdtic.R;
import com.h5200042.hkdtic.adaptor.AdressAdapter;
import com.h5200042.hkdtic.model.AdressModel;

import java.util.ArrayList;
import java.util.List;

public class Adress extends AppCompatActivity {

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    RecyclerView recyclerView;
    AdressAdapter adressAdapter;
    ArrayList<AdressModel> adressModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();



        addAdres();
        getAdres();
        bottomBarOptions();

    }


    public void getAdres(){
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.rcvAdres);

        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        adressModelList = new ArrayList<>();
        adressAdapter = new AdressAdapter(this,adressModelList);
        recyclerView.setAdapter(adressAdapter);

        firestore.collection("Users").document(auth.getCurrentUser().getUid())
                .collection("Adres").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        String documentId = documentSnapshot.getId();

                        AdressModel adressModel = documentSnapshot.toObject(AdressModel.class);

                        adressModelList.add(adressModel);

                        adressModel.setDocumentId(documentId);

                        adressAdapter.notifyDataSetChanged();

                    }
                }
            }
        });


    }


    public void addAdres(){
        TextView addAdres;
        ImageView imgAddAdres;

        addAdres = findViewById(R.id.txt_addAdres);
        imgAddAdres = findViewById(R.id.img_addAdres);

        addAdres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NewAdress.class));
            }
        });
        imgAddAdres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NewAdress.class));
            }
        });


    }

    public void bottomBarOptions() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.Navigationbar);

        //Set home selected
        bottomNavigationView.setSelectedItemId(R.id.ico_cart);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ico_discover:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.ico_categories:
                        startActivity(new Intent(getApplicationContext(), Categories.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.ico_orders:
                        startActivity(new Intent(getApplicationContext(), Orders.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.ico_cart:
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }

        });
    }
}