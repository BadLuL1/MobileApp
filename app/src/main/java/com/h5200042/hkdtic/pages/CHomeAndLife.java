package com.h5200042.hkdtic.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.h5200042.hkdtic.R;
import com.h5200042.hkdtic.adaptor.ProductsAdapter;
import com.h5200042.hkdtic.model.Products;

import java.util.ArrayList;

public class CHomeAndLife extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    ArrayList<Products> list;
    ProductsAdapter productsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chome_and_life);



        bottomBarOptions();
        getProducts();

    }

    public void getProducts(){
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://hkdtic-default-rtdb.europe-west1.firebasedatabase.app");

        recyclerView = findViewById(R.id.rcv_homeandlife);
        database = db.getReference("CHomeAndLife");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        list = new ArrayList<>();
        productsAdapter = new ProductsAdapter(this,list);
        recyclerView.setAdapter(productsAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                    Products products = dataSnapshot.getValue(Products.class);
                    list.add(products);


                }
                productsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void bottomBarOptions(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.Navigationbar);

        //Set home selected
        bottomNavigationView.setSelectedItemId(R.id.ico_categories);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ico_discover:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.ico_categories:
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.ico_orders:
                        startActivity(new Intent(getApplicationContext(),Orders.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.ico_cart:
                        startActivity(new Intent(getApplicationContext(), Cart.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }

        });
    }
}