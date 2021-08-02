package com.freemarket.seller.sellerapi.business.model;

import com.freemarket.seller.sellerapi.business.dto.SellerInput;
import com.freemarket.seller.sellerapi.business.passwordencoder.SellerPasswordEncoder;

public class Seller {

    private String id;

    private final String userName;

    private final String storeName;

    private final String password;

    private Contact contact;

    private SellerPlan sellerPlan;

    public Seller(String userName, String storeName, String password) {
        this.userName = userName;
        this.storeName = storeName;
        this.password = SellerPasswordEncoder.encode(password);
    }

    public static Seller from(SellerInput sellerInput) {
        Seller seller = new Seller(sellerInput.getUserName(), sellerInput.getStoreName(), sellerInput.getPassword());
        seller.contact = new Contact(sellerInput.getEmail(), sellerInput.getPhoneNumber());
        seller.sellerPlan = sellerInput.getSellerPlan();
        return seller;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getPassword() {
        return password;
    }

    public Contact getContact() {
        return contact;
    }

    public SellerPlan getSellerPlan() {
        return sellerPlan;
    }

    public void addId(String id) {
        this.id = id;
    }
}
