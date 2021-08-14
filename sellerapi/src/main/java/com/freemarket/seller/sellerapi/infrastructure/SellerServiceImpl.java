package com.freemarket.seller.sellerapi.infrastructure;

import com.freemarket.seller.sellerapi.api.exceptions.ServiceUnavailableException;
import com.freemarket.seller.sellerapi.business.model.Seller;
import com.freemarket.seller.sellerapi.business.service.SellerService;
import com.freemarket.seller.sellerapi.infrastructure.repository.entity.SellerEntity;
import com.freemarket.seller.sellerapi.infrastructure.repository.SellerRepository;
import com.mongodb.MongoClientException;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller save(Seller seller) {
        try {
            SellerEntity saved = sellerRepository.save(SellerEntity.map(seller));
            seller.addId(saved.getId());
            return seller;
        }catch (MongoClientException mte) {
            throw new ServiceUnavailableException(mte.getMessage(),"/errors/service-unavailable", "SU-1", "Service Unavailable, try again later");
        }
    }
}
