package com.h5200042.hkdtic.model;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOUser
{
    private DatabaseReference databaseReference;

    public DAOUser(){
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://hkdtic-default-rtdb.europe-west1.firebasedatabase.app");
        databaseReference = db.getReference(User.class.getSimpleName());
    }
    public Task<Void> add(User usr){

        return databaseReference.push().setValue(usr);
    }
}
