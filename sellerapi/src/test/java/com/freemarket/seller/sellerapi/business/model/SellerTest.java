package com.freemarket.seller.sellerapi.business.model;

import com.freemarket.seller.sellerapi.business.dto.SellerInput;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class SellerTest {

    @Test
    void givenASellerInput_mustCreateANewSeller() {
        SellerInput sellerInput = SellerInput.builder()
                .userName("newseller")
                .storeName("Best Seller")
                .email("newseller@mail.com")
                .phoneNumber("5551245")
                .password("my-secret-password")
                .sellerPlan(SellerPlan.STORE)
                .build();
        Seller seller = Seller.from(sellerInput);
        assertEquals(sellerInput.getUserName(), seller.getUserName());
        assertEquals(sellerInput.getStoreName(), seller.getStoreName());
        assertEquals(sellerInput.getEmail(), seller.getContact().getEmail());
        assertEquals(sellerInput.getPhoneNumber(), seller.getContact().getPhoneNumber());
        assertEquals(sellerInput.getSellerPlan(), seller.getSellerPlan());
        assertNotEquals(sellerInput.getPassword(), seller.getPassword());
        assertTrue(new BCryptPasswordEncoder().matches(sellerInput.getPassword(), seller.getPassword()));
    }
}