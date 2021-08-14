package com.freemarket.seller.sellerapi.api.dto;

import com.freemarket.seller.sellerapi.business.dto.SellerInput;
import com.freemarket.seller.sellerapi.business.model.SellerPlan;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Builder
public class SellerRequest implements SellerInput {

    @Schema(description = "Seller UserName ")
    @NotEmpty(message = "The username must be filled")
    private String userName;

    @Schema(description = "Seller StoreName ")
    @NotEmpty(message = "The storeName must be filled")
    private String storeName;

    @Schema(description = "Seller password ")
    @NotEmpty(message = "The password must be filled")
    private String password;

    @Schema(description = "Seller Email ")
    @NotEmpty(message = "The email must be filled")
    private String email;

    @Schema(description = "Seller Phone Number ")
    @Pattern(regexp="[\\d]", message = "Must be only numbers" )
    private String phoneNumber;

    @Schema(description = "The type of Plan the seller will choose (INDIVIDUAL, STORE, MEGASTORE)",
           example = "INDIVIDUAL")
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
