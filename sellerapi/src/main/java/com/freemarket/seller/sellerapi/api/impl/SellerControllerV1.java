package com.freemarket.seller.sellerapi.api.impl;

import com.freemarket.seller.sellerapi.api.SellerControllerV1Api;
import com.freemarket.seller.sellerapi.api.dto.SellerRequest;
import com.freemarket.seller.sellerapi.business.dto.SellerOutput;
import com.freemarket.seller.sellerapi.business.usecase.CreateSellerUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/sellers")
@Validated
public class SellerControllerV1 implements SellerControllerV1Api {

    private final CreateSellerUseCase createSellerUseCase;

    public SellerControllerV1(CreateSellerUseCase createSellerUseCase) {
        this.createSellerUseCase = createSellerUseCase;
    }

    @Override
    public SellerOutput create(@Valid SellerRequest sellerRequest) {
        return createSellerUseCase.create(sellerRequest);
    }
}
