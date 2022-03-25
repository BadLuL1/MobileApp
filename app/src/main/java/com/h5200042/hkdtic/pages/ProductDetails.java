package com.h5200042.hkdtic.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.h5200042.hkdtic.R;

public class ProductDetails extends AppCompatActivity {

    TextView txtProductPageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
    }
}