package com.freemarket.seller.sellerapi.business.model;

public class Contact {

    private String email;

    private String phoneNumber;

    public Contact(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
