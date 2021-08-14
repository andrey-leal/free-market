package com.freemarket.seller.sellerapi.api.exceptions.converters;

import com.freemarket.seller.sellerapi.api.exceptions.ExceptionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
@Component
class MethodArgumentTypeMismatchConverter implements ExceptionConverterSupports<MethodArgumentTypeMismatchException> {

  private final String errorCode;
  private final String errorTitle;
  private final String errorMessage;
  private final String errorType;

  public MethodArgumentTypeMismatchConverter(
      @Value("${spring.validation.method_type_mismatch.code:invalid_type}") String errorCode,
      @Value("${spring.validation.message_not_readable.type:/errors/invalid-data-type}") String errorType,
      @Value("${spring.validation.method_type_mismatch.error:#{null}}") String errorTitle,
      @Value("${spring.validation.method_type_mismatch.message:Error converting attribute %s with value '%s'}") String errorMessage) {
    this.errorTitle = errorTitle;
    this.errorType = errorType;
    this.errorMessage = errorMessage;
    this.errorCode = errorCode;
  }

  @Override
  public ExceptionResponse convert(MethodArgumentTypeMismatchException ex, int status) {
    return new ExceptionResponse(LocalDateTime.now(), errorType, errorCode, errorTitle, status,
            String.format(errorMessage, ex.getName(), ex.getValue()), null);
  }

  @Override
  public boolean supports(Exception exception) {
    Class clazz = MethodArgumentTypeMismatchException.class;
    return clazz.equals(exception.getClass());
  }
}
