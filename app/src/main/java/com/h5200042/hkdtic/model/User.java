package com.h5200042.hkdtic.model;

public class User {

    public String name,surname,phoneNumber,email,password;

    public User(){}

    public User(String name, String surname, String phoneNumber, String email, String password){

        this.name=name;
        this.surname=surname;
        this.phoneNumber=phoneNumber;
        this.email=email;
        this.password=password;
    }
}
