package com.freemarket.seller.sellerapi.api.exceptions;


public class ExceptionDetail {

    private String reference;

    private String message;

    public ExceptionDetail(String reference, String message) {
        this.reference = reference;
        this.message = message;
    }

    public String getReference() {
        return reference;
    }

    public String getMessage() {
        return message;
    }
}
