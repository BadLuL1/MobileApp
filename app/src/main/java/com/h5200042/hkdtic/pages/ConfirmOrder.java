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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.h5200042.hkdtic.R;
import com.h5200042.hkdtic.adaptor.CartAdapter;
import com.h5200042.hkdtic.adaptor.ConfirmOrderProductsAdapter;
import com.h5200042.hkdtic.model.CartModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConfirmOrder extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth auth;

    RecyclerView recyclerView;
    ConfirmOrderProductsAdapter confirmOrderProductsAdapter;
    ArrayList<CartModel> cartModelList;
    int total = 0;



    TextView adresName, adresSurname, adresAdres, cardOwner, cardNumber, topProductPrice;
    ImageView goAdres, goCreditCard, goCart;
    Button complateOder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        topProductPrice = findViewById(R.id.txt_total_price);


        goPages();
        pushInfo();
        bottomBarOptions();
        getProducts();


        complateOder = findViewById(R.id.complateShopping2);

        complateOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanCart();
            }
        });

    }

    public void cleanCart(){

            db.collection("AddToCart").document(auth.getCurrentUser().getUid())
                    .collection("CurrentUser").document().delete()
                    //.collection("CurrentUser").document(cartModelList.remove(cartModelList.size()-1).toString()).delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Siparişiniz Onaylandı", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Siparişiniz Onaylanmadı", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }



    public void getProducts() {
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.confirmOrderProducts);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        cartModelList = new ArrayList<>();
        confirmOrderProductsAdapter = new ConfirmOrderProductsAdapter(this, cartModelList);
        recyclerView.setAdapter(confirmOrderProductsAdapter);

        topProductPrice = findViewById(R.id.txt_total_price2); // BURASI DEĞİŞECEK


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


                        confirmOrderProductsAdapter.notifyDataSetChanged();
                    }


                }
                //sepetteki ürünlerin toplam değeri ekrana yazdırılır.
                topProductPrice.setText(String.valueOf(total)); //burası yapılacak değişecek
            }
        });


    }






    public void goPages(){
        goAdres = findViewById(R.id.imageView9);
        goCreditCard = findViewById(R.id.imageView10);
        goCart = findViewById(R.id.imageView11);

        goAdres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Adress.class));
            }
        });

        goCreditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CreditCard.class));
            }
        });

        goCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Cart.class));
            }
        });



    }


    public void pushInfo(){

        //KENDİME NOT ŞÖYLE YAPILACAK SİPARİŞ ONAYLADAN SONRA DİREK ONAYLAMA EKRANI GELECEK
        // ORADAN YENİLEME İKONUNA BASILINCA ADRES FALAN FİSTAN SEÇİLECEK SEÇİLİNCE PUTEXTRA İLE ADRESE GÖNDERECEKSİN
        // KART BİLGİLERİNİ DE ÖYLE GÖNDERECEKSİN EKRANLARIN AÇILMA SIRALARINI DEĞİŞTİRMEKLE İŞE BAŞLA.

        adresName = findViewById(R.id.textView23);
        adresSurname = findViewById(R.id.textView25);
        adresAdres = findViewById(R.id.textView26);
        cardOwner = findViewById(R.id.textView29);
        cardNumber = findViewById(R.id.textView28);

        /*String adresNamee = getIntent().getExtras().getString("adresName","adi");
        String adresSurnamee = getIntent().getExtras().getString("adresSurname","soyadi");
        String adresAdress = getIntent().getExtras().getString("adresAdres","adres");*/
        String cardOwnerr = getIntent().getExtras().getString("cardOwner","kartsahibi");
        String cardNumberr = getIntent().getExtras().getString("cardNumber","kart numarasi");

        /*adresName.setText(adresNamee);
        adresSurname.setText(adresSurnamee);
        adresAdres.setText(adresAdress);*/
        cardOwner.setText(cardOwnerr);
        cardNumber.setText(cardNumberr);


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