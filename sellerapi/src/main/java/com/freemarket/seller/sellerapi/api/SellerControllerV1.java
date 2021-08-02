package com.freemarket.seller.sellerapi.api;

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
public class SellerControllerV1 {

    private final CreateSellerUseCase createSellerUseCase;

    public SellerControllerV1(CreateSellerUseCase createSellerUseCase) {
        this.createSellerUseCase = createSellerUseCase;
    }

    @PostMapping(produces = { "application/hal+json" }, consumes = {"application/hal+json"})
    public ResponseEntity<SellerOutput> create(@RequestBody @Valid SellerRequest sellerRequest) {
        return new ResponseEntity<>(createSellerUseCase.create(sellerRequest), HttpStatus.CREATED);
    }
}
