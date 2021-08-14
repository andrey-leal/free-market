package com.freemarket.seller.sellerapi.api.exceptions.converters;

public interface ExceptionConverterSupports<T> extends IExceptionConverter<T> {

  boolean supports(Exception exception);
}
