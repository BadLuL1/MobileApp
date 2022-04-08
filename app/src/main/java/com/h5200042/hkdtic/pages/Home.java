package com.h5200042.hkdtic.pages;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.h5200042.hkdtic.R;
import com.h5200042.hkdtic.adaptor.ProductsAdapter;
import com.h5200042.hkdtic.model.Products;
import com.h5200042.hkdtic.network.Service;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Home extends AppCompatActivity implements View.OnClickListener {
// kendime son not: veri tabanından ürün çekilecek
// kayıt olan kullanıcılar firebase store da kullanıcıların bilgileri gelecek ad soyad gözükecek.

    private RecyclerView recyclerView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();






        Toolbar toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);


        getProducts();
        bottomBarOptions();



    }



    public void getProducts() {

        new Service().getServiceApi().getProducts().
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Products>>() {

                    List<Products> products = new ArrayList<>();

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Products> productsParam) {
                        products = productsParam;

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if(products.size()>0){
                            initRecycleView(products);
                        }
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

    private void initRecycleView(List<Products> productsList) {
        recyclerView = findViewById(R.id.rcvProducts);

        ProductsAdapter productsAdapter = new ProductsAdapter(productsList, getApplicationContext(), new ProductsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Products clickProducts) {
                Intent DetailsProduct = new Intent(Home.this,ProductDetails.class);
                DetailsProduct.putExtra("txtProductPageName",clickProducts.getTxtProductListName());

                startActivity(DetailsProduct);
            }
        });


        recyclerView.setAdapter(productsAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);





    }







    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.ico_search) {
            Toast.makeText(getApplicationContext(), "Arama butonuna tıklandı.", Toast.LENGTH_LONG).show();
            goPage(Login.class); //arama ikonuna tıklanıldığında giriş ekranına atıyor.
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


    private void goPage(Class<?> cls) {
        Intent intent = new Intent(this, cls);

        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }
}