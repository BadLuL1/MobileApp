package com.h5200042.hkdtic.model;

import java.io.Serializable;

public class AdressModel implements Serializable {
    private String profileName;
    private String name;
    private String surname;
    private String identity;
    private String city;
    private String county;
    private String zipCode;
    private String adres;
    private String phone;
    String documentId;

    public AdressModel() {
    }

    public AdressModel(String profileName, String name, String surname, String identity, String city, String county, String zipCode, String adres, String phone) {
        this.profileName = profileName;
        this.name = name;
        this.surname = surname;
        this.identity = identity;
        this.city = city;
        this.county = county;
        this.zipCode = zipCode;
        this.adres = adres;
        this.phone = phone;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
