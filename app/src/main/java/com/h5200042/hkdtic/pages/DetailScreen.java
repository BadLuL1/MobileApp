package com.h5200042.hkdtic.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.h5200042.hkdtic.R;
import com.h5200042.hkdtic.model.Products;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailScreen extends AppCompatActivity {

    ImageView img_detailPhoto, btn_plus, btn_minus;
    TextView txt_detailProductName;
    TextView txt_sellerName;
    TextView txt_explanation;
    TextView txt_product_price_detail;
    TextView quantity;
    Button btn_addCart;

    int totalQuantity = 1;


    Products products=null;


    int totalPrice = 0;

    FirebaseFirestore firestore;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object object = getIntent().getSerializableExtra("detail");
        if(object instanceof Products){
            products = (Products) object;
        }


        getDetails();
        getProductPiece();
        getCart();
        bottomBarOptions();

    }


    public void getProductPiece(){

        quantity = (TextView) findViewById(R.id.textView34);

        btn_plus = findViewById(R.id.plusCircle);
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantity<20){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    txt_product_price_detail.setText(String.valueOf(totalQuantity * Integer.parseInt(products.txtProductPrice)));
                    totalPrice=totalQuantity * Integer.parseInt(products.txtProductPrice);
                }
            }
        });

        btn_minus = findViewById(R.id.minusCircle);
        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantity>1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    txt_product_price_detail.setText(String.valueOf(totalQuantity * Integer.parseInt(products.txtProductPrice)));
                    totalPrice=totalQuantity * Integer.parseInt(products.txtProductPrice);
                }
            }
        });

    }


    public void getCart(){

        btn_addCart = findViewById(R.id.btn_addCart);

        btn_addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedToCart();
            }
        });

    }

    private void addedToCart() {
        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("productName",products.getTxtProductListName());
        cartMap.put("productPrice",txt_product_price_detail.getText().toString());
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("totalPrice",totalPrice);
        cartMap.put("sellerName",products.getTxtSellerName());
        cartMap.put("productPhotoURL",products.getImgProductList());
        cartMap.put("quantity",totalQuantity);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailScreen.this, "Ürün Sepete Eklendi", Toast.LENGTH_SHORT).show();

            }
        });


    }


    public void getDetails(){

        //E3 E BİR ŞEYLER ÇEKMEDEN ÖNCE DETAY EKRANINDAKİ YERLERİ BURADA TANIMLA.
        img_detailPhoto = findViewById(R.id.img_detailPhoto);
        txt_detailProductName = findViewById(R.id.txt_detailProductName);
        txt_sellerName = findViewById(R.id.txt_sellerName);
        txt_explanation = findViewById(R.id.txt_explanation);
        txt_product_price_detail = findViewById(R.id.txt_product_price_detail);

        //E3 E BİR ŞEYLER EKLEYECEKSEN BURADAN NEREYE NE ÇEKECEĞİNİ BELİRT.
        if(products != null){
            Glide.with(getApplicationContext()).load(products.getImgProductList()).into(img_detailPhoto);
            txt_detailProductName.setText(products.getTxtProductListName());
            txt_explanation.setText(products.getTxtExplanation());
            txt_product_price_detail.setText(String.valueOf(products.getTxtProductPrice()));
            txt_sellerName.setText(products.getTxtSellerName());

            totalPrice = Integer.parseInt(products.getTxtProductPrice());
        }

    }


    public void bottomBarOptions(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.Navigationbar);

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

}