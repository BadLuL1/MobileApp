package com.h5200042.hkdtic.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.h5200042.hkdtic.R;
import com.h5200042.hkdtic.model.AdressModel;

import java.util.HashMap;

public class NewAdress extends AppCompatActivity{

    public FirebaseFirestore firestore;
    public FirebaseAuth auth;
    public EditText edtAdresName, edtName, edtSurname, edtIdNumber, edtCity, edtCounty, edtZipCode, edtAdres, edtPhone;
    public Button save;

    AdressModel adressModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_adress);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        bottomBarOptions();

        final Object object = getIntent().getSerializableExtra("Adres");
        if(object instanceof AdressModel){
            adressModel = (AdressModel) object;
        }


        edtAdresName = (EditText) findViewById(R.id.newAdresAdresAdi);
        edtName = (EditText) findViewById(R.id.newAdresName);
        edtSurname = (EditText) findViewById(R.id.newAdresSurname);
        edtIdNumber = (EditText) findViewById(R.id.newAdAdresTC);
        edtCity = (EditText) findViewById(R.id.newAdAdresIl);
        edtCounty = (EditText) findViewById(R.id.newAdresIlce);
        edtZipCode = (EditText) findViewById(R.id.newAdresPostaKodu);
        edtAdres = (EditText) findViewById(R.id.newAdresAdres);
        edtPhone = (EditText) findViewById(R.id.newAdresTel);

        save = findViewById(R.id.newAdresKaydet);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDB();
            }
        });



    }

    public void sendDB(){
        String adresName = edtAdresName.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String surname = edtSurname.getText().toString().trim();
        String internationalId = edtIdNumber.getText().toString().trim();
        String city = edtCity.getText().toString().trim();
        String county = edtCounty.getText().toString().trim();
        String zipCode = edtZipCode.getText().toString().trim();
        String adres = edtAdres.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();

        if(adresName.isEmpty()){
            edtAdresName.setError("Adres adını lütfen boş bırakmayınız.");
            edtAdresName.requestFocus();
            return;
        }
        if(name.isEmpty()){
            edtName.setError("Lütfen isminizi yazınız.");
            edtName.requestFocus();
            return;
        }if(surname.isEmpty()){
            edtSurname.setError("Lütfen soyadınızı yazınız.");
            edtSurname.requestFocus();
            return;
        }if(internationalId.isEmpty()){
            edtIdNumber.setError("Lütfen 11 haneli T.C kimlik numaranızı giriniz.");
            edtIdNumber.requestFocus();
            return;
        }
        if(city.isEmpty()){
            edtCity.setError("Lütfen il yazınız.");
            edtCity.requestFocus();
            return;
        }
        if(county.isEmpty()){
            edtCounty.setError("Lütfen ilçe yazınız.");
            edtCounty.requestFocus();
            return;
        }
        if(zipCode.isEmpty()){
            edtZipCode.setError("Lütfen posta kodunu yazınız.");
            edtZipCode.requestFocus();
            return;
        }
        if(adres.isEmpty()){
            edtAdres.setError("Lütfen açık adresinizi yazınız.");
            edtAdres.requestFocus();
            return;
        }
        if(phone.isEmpty()){
            edtPhone.setError("Lütfen telefon numaranızı giriniz.");
            edtPhone.requestFocus();
            return;
        }


        final HashMap<String,Object> adresMap = new HashMap<>();
        adresMap.put("profileName",adresName);
        adresMap.put("name",name);
        adresMap.put("surname",surname);
        adresMap.put("identity",internationalId);
        adresMap.put("city",city);
        adresMap.put("county",county);
        adresMap.put("zipCode",zipCode);
        adresMap.put("adres",adres);
        adresMap.put("phone",phone);

        firestore.collection("Users").document(auth.getCurrentUser().getUid())
                .collection("Adres").add(adresMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(getApplicationContext(), "Adres kayıt edildi.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Adress.class));
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