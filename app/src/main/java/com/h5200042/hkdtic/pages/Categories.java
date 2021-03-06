package com.h5200042.hkdtic.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.h5200042.hkdtic.R;
import com.h5200042.hkdtic.model.CartModel;

public class Categories extends AppCompatActivity{

    ImageView autoAccessory, electronic, gift, homeAndLife, momAndBaby, personalCare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);


        choseCategory();
        bottomBarOptions();


    }

    public void choseCategory(){
        electronic = findViewById(R.id.btn_elektronik);
        homeAndLife = findViewById(R.id.btn_evyasam);
        momAndBaby = findViewById(R.id.btn_annebebek);
        personalCare = findViewById(R.id.btn_kisbakim);
        gift = findViewById(R.id.btn_hediyelik);
        autoAccessory = findViewById(R.id.btn_otoaksesuar);

        autoAccessory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CAutoAccessory.class));
            }
        });

        electronic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CElectronic.class));
            }
        });

        gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CGift.class));
            }
        });

        homeAndLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CHomeAndLife.class));
            }
        });

        momAndBaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CMomAndBaby.class));
            }
        });

        personalCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CPersonalCare.class));
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