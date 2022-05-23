package com.h5200042.hkdtic.pages;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.h5200042.hkdtic.R;
import com.h5200042.hkdtic.adaptor.ProductsAdapter;
import com.h5200042.hkdtic.model.Products;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Home extends AppCompatActivity{

    RecyclerView recyclerView;
    FirebaseAuth mAuth;
    DatabaseReference database;
    ProductsAdapter productsAdapter;
    ArrayList<Products> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);


        bottomBarOptions();
        getProducts();



    }


    public void getProducts(){
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://hkdtic-default-rtdb.europe-west1.firebasedatabase.app");

        recyclerView = findViewById(R.id.rcvProducts);
        database = db.getReference("Products");
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
        bottomNavigationView.setSelectedItemId(R.id.ico_discover);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ico_discover:
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.ico_categories:
                        startActivity(new Intent(getApplicationContext(),Categories.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.ico_orders:
                        startActivity(new Intent(getApplicationContext(),Orders.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.ico_cart:
                        startActivity(new Intent(getApplicationContext(),Cart.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }

        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.ico_search) {
            Toast.makeText(getApplicationContext(), "Arama butonuna tıklandı.", Toast.LENGTH_LONG).show();
        } else if (id == R.id.ico_bookmark) {
            Toast.makeText(getApplicationContext(), "Kaydedilenler butonuna tıklandı.", Toast.LENGTH_LONG).show();
        } else if (id == R.id.ico_profile) {
            Toast.makeText(getApplicationContext(), "Profil butonuna tıklandı.", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topmenu, menu);
        return true;
    }
}