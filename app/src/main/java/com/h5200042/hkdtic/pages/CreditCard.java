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
import com.h5200042.hkdtic.adaptor.CreditCardAdapter;
import com.h5200042.hkdtic.model.CreditCardModel;

import java.util.ArrayList;

public class CreditCard extends AppCompatActivity {

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    RecyclerView recyclerView;

    CreditCardAdapter creditCardAdapter;
    ArrayList<CreditCardModel> creditCardModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);

        getCards();
        bottomBarOptions();
        addCreditCard();

        /*TextView adresName = findViewById(R.id.textView23);
        TextView adresSurname = findViewById(R.id.textView25);
        TextView adresAdres = findViewById(R.id.textView26);

        String adresNamee = getIntent().getExtras().getString("adresName");
        String adresSurnamee = getIntent().getExtras().getString("adresSurname");
        String adresAdress = getIntent().getExtras().getString("adresAdres");

        adresName.setText(adresNamee);
        adresSurname.setText(adresSurnamee);
        adresAdres.setText(adresAdress);*/



    }

    public void getCards(){

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.rvc_cards);

        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        creditCardModelList = new ArrayList<>();
        creditCardAdapter = new CreditCardAdapter(this,creditCardModelList);
        recyclerView.setAdapter(creditCardAdapter);

        firestore.collection("Users").document(auth.getCurrentUser().getUid())
                .collection("CreditCard").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                        CreditCardModel creditCardModel = documentSnapshot.toObject(CreditCardModel.class);
                        String documentId = documentSnapshot.getId();

                        creditCardModelList.add(creditCardModel);
                        creditCardModel.setDocumentId(documentId);

                        creditCardAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }

    public void addCreditCard(){

        TextView addAdres1;
        ImageView imgAddAdres1;

        addAdres1 = findViewById(R.id.txt_addCard);
        imgAddAdres1 = findViewById(R.id.img_addCard);

        addAdres1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NewCreditCard.class));
            }
        });
        imgAddAdres1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NewCreditCard.class));
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