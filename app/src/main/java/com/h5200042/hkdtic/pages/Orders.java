package com.h5200042.hkdtic.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.h5200042.hkdtic.R;

public class Orders extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        bottomBarOptions();
    }

    public void bottomBarOptions(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.Navigationbar);

        //Set home selected
        bottomNavigationView.setSelectedItemId(R.id.ico_orders);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ico_discover:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.ico_categories:
                        startActivity(new Intent(getApplicationContext(),Categories.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.ico_orders:
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

    private void goPage(Class<?> cls) {
        Intent intent = new Intent(this, cls);

        startActivity(intent);
    }
}