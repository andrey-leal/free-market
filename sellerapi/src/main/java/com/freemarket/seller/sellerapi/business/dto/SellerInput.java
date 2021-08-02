package com.freemarket.seller.sellerapi.business.dto;

import com.freemarket.seller.sellerapi.business.model.SellerPlan;

public interface SellerInput {

    String getUserName();

    String getStoreName();

    String getPassword();

    String getEmail();

    String getPhoneNumber();

    SellerPlan getSellerPlan();
}
