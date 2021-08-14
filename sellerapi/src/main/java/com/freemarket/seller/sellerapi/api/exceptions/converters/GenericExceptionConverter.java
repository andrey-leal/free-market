package com.freemarket.seller.sellerapi.api.exceptions.converters;

import com.freemarket.seller.sellerapi.api.exceptions.ExceptionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
class GenericExceptionConverter implements IExceptionConverter<Exception> {

  private final String errorCode;
  private final String errorTitle;
  private final String type;

  public GenericExceptionConverter(
          @Value("${spring.validation.default_exception.code:error}") String errorCode,
          @Value("${spring.validation.default_exception.error:#{null}}") String errorTitle,
          @Value("${spring.validation.default_exception.type:/errors/unknown-error") String type) {
    this.errorTitle = errorTitle;
    this.errorCode = errorCode;
    this.type = type;
  }

  @Override
  public ExceptionResponse convert(Exception ex, int value) {
    return new ExceptionResponse(LocalDateTime.now(), type, errorCode, errorTitle, value, ex.getMessage(), null);
  }
}
