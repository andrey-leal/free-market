package com.freemarket.seller.sellerapi.api.dto;

import com.freemarket.seller.sellerapi.business.dto.SellerInput;
import com.freemarket.seller.sellerapi.business.model.SellerPlan;
import lombok.Builder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Builder
public class SellerRequest implements SellerInput {

    @NotEmpty(message = "The username must be filled")
    private String userName;

    @NotEmpty(message = "The storeName must be filled")
    private String storeName;

    @NotEmpty(message = "The password must be filled")
    private String password;

    @NotEmpty(message = "The email must be filled")
    private String email;

    @Pattern(regexp="[\\d]", message = "Must be only numbers" )
    private String phoneNumber;

    @NotNull
    private SellerPlan sellerPlan;

    @Override
    public String getUserName() {
        return userName;
    }
    @Override
    public String getStoreName() {
        return storeName;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getEmail() {
        return email;
    }
    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }
    @Override
    public SellerPlan getSellerPlan() {
        return sellerPlan;
    }
}
