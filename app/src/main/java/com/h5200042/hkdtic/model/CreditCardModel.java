package com.h5200042.hkdtic.model;

import java.io.Serializable;

public class CreditCardModel implements Serializable {
    private String cardOwnerName;
    private String cardNumber;
    private String cardExpirationDateMonth;
    private String getCardExpirationDateYear;
    private String cardCVV;
    private String documentId;

    public CreditCardModel(){

    }

    public CreditCardModel(String cardOwnerName, String cardNumber, String cardExpirationDateMonth, String getCardExpirationDateYear, String cardCVV) {
        this.cardOwnerName = cardOwnerName;
        this.cardNumber = cardNumber;
        this.cardExpirationDateMonth = cardExpirationDateMonth;
        this.getCardExpirationDateYear = getCardExpirationDateYear;
        this.cardCVV = cardCVV;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getCardOwnerName() {
        return cardOwnerName;
    }

    public void setCardOwnerName(String cardOwnerName) {
        this.cardOwnerName = cardOwnerName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpirationDateMonth() {
        return cardExpirationDateMonth;
    }

    public void setCardExpirationDateMonth(String cardExpirationDateMonth) {
        this.cardExpirationDateMonth = cardExpirationDateMonth;
    }

    public String getGetCardExpirationDateYear() {
        return getCardExpirationDateYear;
    }

    public void setGetCardExpirationDateYear(String getCardExpirationDateYear) {
        this.getCardExpirationDateYear = getCardExpirationDateYear;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
    }
}
