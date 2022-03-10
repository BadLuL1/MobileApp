package com.h5200042.hkdtic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.btn_uye_ol);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(this,SignUp.class);

            startActivity(intent);
        });

    }

}
