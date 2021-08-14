package com.freemarket.seller.sellerapi.api.exceptions.converters;


import com.freemarket.seller.sellerapi.api.exceptions.ExceptionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
class HttpMessageNotReadableConverter implements ExceptionConverterSupports<HttpMessageNotReadableException> {

  private final String errorCode;
  private final String errorType;
  private final String errorTitle;
  private final String errorMessage;

  public HttpMessageNotReadableConverter(
          @Value("${spring.validation.message_not_readable.code:invalid_request}") String errorCode,
          @Value("${spring.validation.message_not_readable.type:/errors/invalid-call}") String errorType,
          @Value("${spring.validation.message_not_readable.error:#{null}}") String errorTitle,
          @Value("${spring.validation.message_not_readable.message:#{null}}") String errorMessage) {
    this.errorType = errorType;
    this.errorTitle = errorTitle;
    this.errorMessage = errorMessage;
    this.errorCode = errorCode;
  }

  @Override
  public ExceptionResponse convert(HttpMessageNotReadableException ex, int status) {
    String exceptionMessage = errorMessage;
    if (exceptionMessage == null) {
      exceptionMessage = ex.getMessage();
    }
    return new ExceptionResponse(LocalDateTime.now(), errorType, errorCode, errorTitle, status, exceptionMessage, null);
  }

  @Override
  public boolean supports(Exception exception) {
    Class clazz = HttpMessageNotReadableException.class;
    return clazz.equals(exception.getClass());
  }
}
