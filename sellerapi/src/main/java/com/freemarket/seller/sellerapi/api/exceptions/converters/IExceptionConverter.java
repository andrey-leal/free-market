package com.freemarket.seller.sellerapi.api.exceptions.converters;

import com.freemarket.seller.sellerapi.api.exceptions.ExceptionResponse;

public interface IExceptionConverter<T> {

  ExceptionResponse convert(T ex, int value);

}