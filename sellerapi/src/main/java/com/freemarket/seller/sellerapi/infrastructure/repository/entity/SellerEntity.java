package com.freemarket.seller.sellerapi.infrastructure.repository.entity;

import com.freemarket.seller.sellerapi.business.model.Seller;
import com.freemarket.seller.sellerapi.business.model.SellerPlan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Sellers")
public class SellerEntity {

    @Id
    private String id;

    private String userName;

    private String storeName;

    private String password;

    private String email;

    private String phoneNumber;

    private SellerPlan sellerPlan;

    public static SellerEntity map(Seller seller) {
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.id = seller.getId();
        sellerEntity.userName = seller.getUserName();
        sellerEntity.storeName = seller.getStoreName();
        sellerEntity.password = seller.getPassword();
        sellerEntity.email = seller.getContact().getEmail();
        sellerEntity.phoneNumber = seller.getContact().getPhoneNumber();
        sellerEntity.sellerPlan = seller.getSellerPlan();
        return sellerEntity;
    }

    public String getId() {
        return id;
    }
}
