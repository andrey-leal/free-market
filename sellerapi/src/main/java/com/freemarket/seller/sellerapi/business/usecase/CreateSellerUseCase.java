package com.freemarket.seller.sellerapi.business.usecase;

import com.freemarket.seller.sellerapi.business.dto.SellerInput;
import com.freemarket.seller.sellerapi.business.dto.SellerOutput;

public interface CreateSellerUseCase {
    SellerOutput create(SellerInput sellerInput);
}
