package com.h5200042.hkdtic.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.h5200042.hkdtic.R;
import com.h5200042.hkdtic.model.AdressModel;
import com.h5200042.hkdtic.model.CreditCardModel;

import java.util.HashMap;

public class NewCreditCard extends AppCompatActivity {

    public FirebaseFirestore firestore;
    public FirebaseAuth auth;
    public EditText edtCardOwnerName, edtCardNumber, edtCardExpirationDateMonth, edtCardExpirationDateYear, edtCardCVV;
    public ImageView info;
    public Button save;

    CreditCardModel creditCardModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_credit_card);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


        bottomBarOptions();

        final Object object = getIntent().getSerializableExtra("KrediKarti");
        if(object instanceof CreditCardModel){
            creditCardModel = (CreditCardModel) object;
        }

        edtCardOwnerName = (EditText) findViewById(R.id.cardName);
        edtCardNumber = (EditText) findViewById(R.id.cardNumber);
        edtCardExpirationDateMonth = (EditText) findViewById(R.id.cardExpirationMonth);
        edtCardExpirationDateYear = (EditText) findViewById(R.id.cardExpirationYear);
        edtCardCVV = (EditText) findViewById(R.id.cardCVV);

        info = findViewById(R.id.img_info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Info.class));
            }
        });

        save = findViewById(R.id.newCardSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDb();
            }
        });



    }


    public void sendDb(){
        String cardOwnerName = edtCardOwnerName.getText().toString().trim();
        String cardNumber = edtCardNumber.getText().toString().trim();
        String cardExpirationDateMonth = edtCardExpirationDateMonth.getText().toString().trim();
        String cardExpirationDateYear = edtCardExpirationDateYear.getText().toString().trim();
        String cardCVV = edtCardCVV.getText().toString().trim();

        if(cardOwnerName.isEmpty()){

            edtCardOwnerName.setError("Kart sahibinin adını ve soyadını giriniz.");
            edtCardOwnerName.requestFocus();
            return;
        }if(cardNumber.isEmpty()){

            edtCardNumber.setError("16 Hanelik kart numarasını giriniz.");
            edtCardNumber.requestFocus();
            return;
        }
        if(cardExpirationDateMonth.isEmpty()){

            edtCardExpirationDateMonth.setError("Kartın son kullanım tarihi boş bırakılamaz. (AY)");
            edtCardExpirationDateMonth.requestFocus();
            return;
        }
        if(cardExpirationDateYear.isEmpty()){

            edtCardExpirationDateYear.setError("Kartın son kullanım tarihi boş bırakılamaz. (YIL)");
            edtCardExpirationDateYear.requestFocus();
            return;
        }
        if(cardCVV.isEmpty()){

            edtCardCVV.setError("3 Haneli CVV kodunuz giriniz.");
            edtCardCVV.requestFocus();
            return;
        }

        final HashMap<String,Object> cardMap = new HashMap<>();
        cardMap.put("cardOwnerName",cardOwnerName);
        cardMap.put("cardNumber",cardNumber);
        cardMap.put("cardExpirationDateMonth",cardExpirationDateMonth);
        cardMap.put("getCardExpirationDateYear",cardExpirationDateYear);
        cardMap.put("cardCVV",cardCVV);

        firestore.collection("Users").document(auth.getCurrentUser().getUid())
                .collection("CreditCard").add(cardMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(getApplicationContext(), "Kartınız kayıt edilmiştir.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),CreditCard.class));
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