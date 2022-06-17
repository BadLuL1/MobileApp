package com.h5200042.hkdtic.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.h5200042.hkdtic.R;

public class Profile extends AppCompatActivity {

    TextInputLayout edtName, edtSurname, edtEmail, edtPhoneNumber, edtPassword;
    TextView phoneNumber, email, password;
    String _name, _surname, _email, _phoneNumber, _password;

    DatabaseReference reference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        reference = FirebaseDatabase.getInstance().getReference("User");
        auth = FirebaseAuth.getInstance();

        phoneNumber = findViewById(R.id.p_inputphonenumber);
        email = findViewById(R.id.p_inputemail);
        password = findViewById(R.id.p_inputpassword);
        edtName = findViewById(R.id.p_name);
        edtSurname = findViewById(R.id.p_surname);
        edtEmail = findViewById(R.id.p_email);
        edtPhoneNumber = findViewById(R.id.p_phoneNumber);
        edtPassword = findViewById(R.id.p_password);


        bottomBarOptions();
        showAllUserData();


    }

    public void showAllUserData() {

        _name = reference.child(auth.getCurrentUser().getUid()).child("name").toString().trim();
        _surname = reference.child(auth.getCurrentUser().getUid()).child("surname").toString().trim();
        _email = reference.child(auth.getCurrentUser().getUid()).child("email").toString().trim();
        _phoneNumber = reference.child(auth.getCurrentUser().getUid()).child("phoneNumber").toString().trim();
        _password = reference.child(auth.getCurrentUser().getUid()).child("password").toString().trim();


        phoneNumber.setText(_phoneNumber);
        email.setText(_email);
        password.setText(_password);
        edtName.getEditText().setText(_name);
        edtSurname.getEditText().setText(_surname);
        edtEmail.getEditText().setText(_email);
        edtPhoneNumber.getEditText().setText(_phoneNumber);
        edtPassword.getEditText().setText(_password);
    }

    public void update(View view) {

        if (isEmailChanged() || isPasswordChanged() || isPhoneNumberChanged()) {

            Toast.makeText(this, "Bilgiler güncellenmiştir.", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Bilgileriniz aynı bilgileriniz güncellenemedi!", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean isEmailChanged() {

        if (!_email.equals(edtEmail.getEditText().getText().toString())) {

            reference.child(auth.getCurrentUser().getUid()).child("email").setValue(edtEmail.getEditText().getText().toString());
            return true;

        } else {
            return false;
        }

    }

    public boolean isPasswordChanged() {

        if (!_password.equals(edtPassword.getEditText().getText().toString())) {

            reference.child(auth.getCurrentUser().getUid()).child("password").setValue(edtPassword.getEditText().getText().toString());
            return true;

        } else {
            return false;
        }

    }

    public boolean isPhoneNumberChanged() {

        if (!_phoneNumber.equals(edtPhoneNumber.getEditText().getText().toString())) {

            reference.child(auth.getCurrentUser().getUid()).child("phoneNumber").setValue(edtPhoneNumber.getEditText().getText().toString());
            return true;

        } else {
            return false;
        }

    }

    public void bottomBarOptions() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.Navigationbar);

        //Set home selected
        bottomNavigationView.setSelectedItemId(R.id.ico_discover);

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
                        startActivity(new Intent(getApplicationContext(), Cart.class));
                        overridePendingTransition(0, 0);
                        return true;
                }

                return false;
            }

        });
    }
}