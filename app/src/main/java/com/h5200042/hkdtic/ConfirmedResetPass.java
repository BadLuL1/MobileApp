package com.h5200042.hkdtic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ConfirmedResetPass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed_reset_pass);

        Button button = findViewById(R.id.btn_go_login_screen);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        });

    }
}