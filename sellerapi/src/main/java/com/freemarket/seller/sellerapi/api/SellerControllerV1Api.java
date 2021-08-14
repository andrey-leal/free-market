package com.freemarket.seller.sellerapi.api;

import com.freemarket.seller.sellerapi.api.exceptions.ExceptionResponse;
import com.freemarket.seller.sellerapi.api.dto.SellerRequest;
import com.freemarket.seller.sellerapi.business.dto.SellerOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;


@Tag(name = "Seller API",
        description = "Create, Find and Edit a Seller")
public interface SellerControllerV1Api {

    @Operation(summary = "Create a new Seller",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "Seller created"),
                    @ApiResponse(responseCode = "400",
                            description = "Invalid Request Data",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))},
            method = "create",
            tags = {"Seller V1",})
    @PostMapping(produces = { "application/hal+json" },
                 consumes = {"application/hal+json"})
    @ResponseStatus(HttpStatus.CREATED)
    SellerOutput create(@RequestBody @Valid SellerRequest sellerRequest);

}
