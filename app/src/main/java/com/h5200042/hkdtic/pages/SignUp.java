package com.h5200042.hkdtic.pages;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.h5200042.hkdtic.R;
import com.h5200042.hkdtic.model.DAOUser;
import com.h5200042.hkdtic.model.User;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private TextView registerUser;
    private EditText editTextName, editTextSurname, editTextPhoneNumber, editTextEmail, editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();


        registerUser = (Button) findViewById(R.id.btn_kayit_ol);
        registerUser.setOnClickListener(this);

        editTextName = (EditText) findViewById(R.id.name_SignUp);
        editTextSurname = (EditText) findViewById(R.id.surname_SignUp);
        editTextEmail = (EditText) findViewById(R.id.e_mail_SignUp);
        editTextPhoneNumber = (EditText) findViewById(R.id.phoneNumber_SignUp);
        editTextPassword = (EditText) findViewById(R.id.password_SignUp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_kayit_ol:
                registerUser();
                break;

        }
    }

    private void registerUser(){

        String name= editTextName.getText().toString().trim();
        String surname= editTextSurname.getText().toString().trim();
        String email= editTextEmail.getText().toString().trim();
        String phoneNumber= editTextPhoneNumber.getText().toString().trim();
        String password= editTextPassword.getText().toString().trim();


        if(name.isEmpty()){
            editTextName.setError("??sim bo?? b??rak??lamaz!");
            editTextName.requestFocus();
            return;
        }
        if(surname.isEmpty()){
            editTextSurname.setError("Soyad?? bo?? b??rak??lamaz!");
            editTextSurname.requestFocus();
            return;
        }
        if(phoneNumber.isEmpty()){
            editTextPhoneNumber.setError("Telefon Numaras?? bo?? b??rak??lamaz!");
            editTextPhoneNumber.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editTextEmail.setError("E-mail bo?? b??rak??lamaz!");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("??ifre bo?? b??rak??lamaz!");
            editTextPassword.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("L??tfen ge??erli bir e-mail giriniz.");
            editTextName.requestFocus();
            return;
        }
        if(password.length() < 6){
            editTextPassword.setError("??ifre en az 6 karakterden olu??mal??d??r.");
            editTextPassword.requestFocus();
            return;
        }



        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(name,surname,email,phoneNumber,password);

                            Toast.makeText(SignUp.this, "Ba??ar??l?? bir ??ekilde kay??t yap??ld??!", Toast.LENGTH_LONG).show();

                            DAOUser dao = new DAOUser();
                            User usr = new User(editTextName.getText().toString(),editTextSurname.getText().toString(),
                                    editTextPhoneNumber.getText().toString(),editTextEmail.getText().toString(),
                                    editTextPassword.getText().toString());
                            dao.add(usr).addOnSuccessListener(suc->
                            {
                                Toast.makeText(getApplicationContext(),"Kay??t al??nd??.",Toast.LENGTH_SHORT).show();
                            }).addOnFailureListener(er->
                            {
                                Toast.makeText(getApplicationContext(), ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                            });

                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){}
                                    else{
                                        Toast.makeText(SignUp.this, "Kay??t ba??ar??s??z, tekrar deneyin!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });


                        }else{
                            Toast.makeText(SignUp.this, "Kay??t ba??ar??s??z, tekrar deneyin!", Toast.LENGTH_LONG).show();
                        }
                        startActivity(new Intent(getApplicationContext(),Login.class));
                    }
                });


    }

}
