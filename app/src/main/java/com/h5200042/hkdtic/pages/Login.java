package com.h5200042.hkdtic.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.h5200042.hkdtic.R;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;


    private EditText editTextEmail, editTextPassword;
    private Button signIn;

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

        signIn = (Button) findViewById(R.id.btn_giris_yap); //E POSTA DOĞRULAMASI İÇİN BU 2 SATIRI YORUM SATIRINDAN ÇIKAR.
        signIn.setOnClickListener(this);                    //E- Posta doğrulaması için.

        editTextEmail = (EditText) findViewById(R.id.emailReset);
        editTextPassword= (EditText) findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();

        /*Button buttonHome = findViewById(R.id.btn_giris_yap); // BURADA EPOSTA DOĞRULAMASI YAPMIYOR PASİF HALE GEÇİYOR.

        buttonHome.setOnClickListener(v -> {
            Intent intent3 = new Intent(this, Home.class);

            startActivity(intent3);
        });*/

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_giris_yap:
                userLogin();
                break;

        }

    }



    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        if(email.isEmpty()){
            editTextEmail.setError("E-mail boş bırakılamaz");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Lütfen geçerli bir mail adresi giriniz.");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Şifre boş bıraklamaz");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    assert user != null;
                    if(user.isEmailVerified()){
                        //redirect
                        startActivity(new Intent(Login.this, Home.class));
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(Login.this,"Lütfen E-mail adresinize gelen mesajı onaylayın!",Toast.LENGTH_LONG).show();
                    }


                }
                else{
                    Toast.makeText(Login.this, "Hatalı e-posta veya şifre", Toast.LENGTH_LONG).show();
                }
            }
        });




    }
}
