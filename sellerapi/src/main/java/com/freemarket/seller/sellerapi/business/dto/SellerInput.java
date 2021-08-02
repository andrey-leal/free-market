package com.freemarket.seller.sellerapi.business.dto;

import com.freemarket.seller.sellerapi.business.model.SellerPlan;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SellerInput {

    private String userName;

    private String storeName;

    private String password;

    private String email;

    private String phoneNumber;

    private SellerPlan sellerPlan;

}
