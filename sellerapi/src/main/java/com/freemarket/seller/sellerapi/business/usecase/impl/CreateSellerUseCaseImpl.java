package com.freemarket.seller.sellerapi.business.usecase.impl;

import com.freemarket.seller.sellerapi.business.dto.SellerInput;
import com.freemarket.seller.sellerapi.business.dto.SellerOutput;
import com.freemarket.seller.sellerapi.business.model.Seller;
import com.freemarket.seller.sellerapi.business.service.SellerService;
import com.freemarket.seller.sellerapi.business.usecase.CreateSellerUseCase;


public class CreateSellerUseCaseImpl implements CreateSellerUseCase {

    private final SellerService sellerService;

    public CreateSellerUseCaseImpl(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    public SellerOutput create(SellerInput sellerInput) {
        Seller seller = sellerService.save(Seller.from(sellerInput));
        return SellerOutput.mapFrom(seller);
    }
}
