package com.freemarket.seller.sellerapi.business.dto;

import com.freemarket.seller.sellerapi.business.model.Seller;
import com.freemarket.seller.sellerapi.business.model.SellerPlan;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SellerOutput {

    private String id;

    private String userName;

    private String storeName;

    private String email;

    private String phoneNumber;

    private SellerPlan sellerPlan;

    public static SellerOutput mapFrom(Seller seller) {
        return SellerOutput.builder()
                .id(seller.getId())
                .userName(seller.getUserName())
                .storeName(seller.getStoreName())
                .email(seller.getContact().getEmail())
                .phoneNumber(seller.getContact().getPhoneNumber())
                .sellerPlan(seller.getSellerPlan())
                .build();
    }
}
