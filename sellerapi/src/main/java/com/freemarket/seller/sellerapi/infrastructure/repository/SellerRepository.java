package com.freemarket.seller.sellerapi.infrastructure.repository;

import com.freemarket.seller.sellerapi.infrastructure.repository.entity.SellerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellerRepository extends MongoRepository<SellerEntity, String> {
}
