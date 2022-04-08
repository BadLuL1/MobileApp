package com.h5200042.hkdtic.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.h5200042.hkdtic.R;
import com.h5200042.hkdtic.pages.ConfirmedResetPass;

public class EnterEmail extends AppCompatActivity {

    private EditText emailEditText;
    private Button resetPasswordButton;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_email);

        emailEditText = (EditText) findViewById(R.id.emailReset);
        resetPasswordButton = (Button) findViewById(R.id.btn_resetPass);

        auth = FirebaseAuth.getInstance();

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });





    }


    private void resetPassword(){
        String email= emailEditText.getText().toString().trim();

        if(email.isEmpty()){
            emailEditText.setError("E-mail alanı doldurulmalıdır.");
            emailEditText.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Lütfen geçerli bir e-mail giriniz.");
            emailEditText.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(EnterEmail.this,"Şifrenizi sıfırlamak için E-postanızı kontrol ediniz.",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(EnterEmail.this, ConfirmedResetPass.class));
                }
                else{
                    Toast.makeText(EnterEmail.this,"asd",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}