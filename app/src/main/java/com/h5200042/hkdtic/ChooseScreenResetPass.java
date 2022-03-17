package com.h5200042.hkdtic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ChooseScreenResetPass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_screen_reset_pass);


        Button button = findViewById(R.id.btn_choose_send_mail);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(this,EnterEmail.class);
            startActivity(intent);
        });



    }
}