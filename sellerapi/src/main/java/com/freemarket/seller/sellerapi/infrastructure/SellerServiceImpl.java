package com.freemarket.seller.sellerapi.infrastructure;

import com.freemarket.seller.sellerapi.business.model.Seller;
import com.freemarket.seller.sellerapi.business.service.SellerService;
import com.freemarket.seller.sellerapi.infrastructure.repository.entity.SellerEntity;
import com.freemarket.seller.sellerapi.infrastructure.repository.SellerRepository;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller save(Seller seller) {
        SellerEntity saved = sellerRepository.save(SellerEntity.map(seller));
        seller.addId(saved.getId());
        return seller;
    }
}
