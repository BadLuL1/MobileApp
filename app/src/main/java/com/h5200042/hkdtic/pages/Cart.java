package com.h5200042.hkdtic.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.h5200042.hkdtic.R;
import com.h5200042.hkdtic.adaptor.CartAdapter;
import com.h5200042.hkdtic.model.CartModel;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth auth;

    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    ArrayList<CartModel> cartModelList;
    int total = 0;

    TextView topProductPrice;
    Button complateShop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        getProducts();
        bottomBarOptions();
        complate();


    }

    public void complate() {

        complateShop = findViewById(R.id.complateShopping);

        complateShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Adress.class));
            }
        });


    }

    public void getProducts() {
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.rcv_cartList);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        cartModelList = new ArrayList<>();
        cartAdapter = new CartAdapter(this, cartModelList);
        recyclerView.setAdapter(cartAdapter);

        topProductPrice = findViewById(R.id.txt_total_price);


        db.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        String documentId = documentSnapshot.getId();

                        CartModel cartModel = documentSnapshot.toObject(CartModel.class);

                        cartModel.setDocumentId(documentId);

                        cartModelList.add(cartModel);

                        //Burada sepetteki toplam fiyat kısmının kodlaması var.
                        for (int i = cartModelList.size(); i > 0; i--) {
                            total += Integer.parseInt(cartModel.getProductPrice())*cartModel.getQuantity();//HATALI

                        }


                        cartAdapter.notifyDataSetChanged();
                    }


                }
                //sepetteki ürünlerin toplam değeri ekrana yazdırılır.
                topProductPrice.setText(String.valueOf(total));
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