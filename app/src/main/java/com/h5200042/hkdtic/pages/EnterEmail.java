package com.h5200042.hkdtic.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.h5200042.hkdtic.R;
import com.h5200042.hkdtic.pages.ConfirmedResetPass;

public class EnterEmail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_email);

        Button button = findViewById(R.id.btn_confirmation);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, ConfirmedResetPass.class);
            startActivity(intent);
        });

    }
}