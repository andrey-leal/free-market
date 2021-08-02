package com.freemarket.seller.sellerapi.business.usecase.impl;

import com.freemarket.seller.sellerapi.api.dto.SellerRequest;
import com.freemarket.seller.sellerapi.business.dto.SellerInput;
import com.freemarket.seller.sellerapi.business.dto.SellerOutput;
import com.freemarket.seller.sellerapi.business.model.Seller;
import com.freemarket.seller.sellerapi.business.model.SellerPlan;
import com.freemarket.seller.sellerapi.business.service.SellerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateSellerUseCaseImplTest {

    private CreateSellerUseCaseImpl createSellerUseCase;

    @Mock
    private SellerService sellerService;

    @BeforeEach
    public void init() {
        createSellerUseCase = new CreateSellerUseCaseImpl(sellerService);
    }

    @Test
    void givenASellerInput_mustReturnASellerOutput() {
        SellerRequest sellerInput = SellerRequest.builder()
                .userName("newseller")
                .storeName("Best Seller")
                .email("newseller@mail.com")
                .phoneNumber("5551245")
                .password("my-secret-password")
                .sellerPlan(SellerPlan.STORE)
                .build();
        Seller savedSeller = Seller.from(sellerInput);
        when(sellerService.save(any(Seller.class))).thenReturn(savedSeller);
        SellerOutput sellerOutput = createSellerUseCase.create(sellerInput);
        assertEquals(sellerOutput.getId(), savedSeller.getId());
        assertEquals(sellerOutput.getUserName(), savedSeller.getUserName());
        assertEquals(sellerOutput.getStoreName(), savedSeller.getStoreName());
        assertEquals(sellerOutput.getPhoneNumber(), savedSeller.getContact().getPhoneNumber());
        assertEquals(sellerOutput.getEmail(), savedSeller.getContact().getEmail());
        assertEquals(sellerOutput.getSellerPlan(), savedSeller.getSellerPlan());
    }

}