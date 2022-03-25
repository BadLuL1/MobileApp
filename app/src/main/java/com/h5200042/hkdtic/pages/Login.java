package com.h5200042.hkdtic.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.h5200042.hkdtic.R;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button buttonSignUp = findViewById(R.id.btn_uye_ol);

        buttonSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUp.class);

            startActivity(intent);
        });

        Button buttonForgetPassword = findViewById(R.id.btn_sifremi_unuttum);

        buttonForgetPassword.setOnClickListener(v -> {
            Intent intent2 = new Intent(this, ChooseScreenResetPass.class);

            startActivity(intent2);
        });

        Button buttonHome = findViewById(R.id.btn_giris_yap);

        buttonHome.setOnClickListener(v -> {
            Intent intent3 = new Intent(this, Home.class);

            startActivity(intent3);
        });

    }

}
