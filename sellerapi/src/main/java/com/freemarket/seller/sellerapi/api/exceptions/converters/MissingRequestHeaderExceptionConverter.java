package com.freemarket.seller.sellerapi.api.exceptions.converters;

import com.freemarket.seller.sellerapi.api.exceptions.ExceptionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingRequestHeaderException;

import java.time.LocalDateTime;

@Component
public class MissingRequestHeaderExceptionConverter implements ExceptionConverterSupports<MissingRequestHeaderException> {

  private final String errorCode;
  private final String errorTitle;
  private final String errorMessage;
  private final String errorType;

  public MissingRequestHeaderExceptionConverter(
      @Value("${spring.validation.header_missing.code:missing_header}") String errorCode,
      @Value("${spring.validation.message_not_readable.type:/errors/invalid-call}") String errorType,
      @Value("${spring.validation.header_missing.error:#{null}}") String errorTitle,
      @Value("${spring.validation.header_missing.message:Required Header '%s' not sent}") String errorMessage) {
    this.errorTitle = errorTitle;
    this.errorType = errorType;
    this.errorMessage = errorMessage;
    this.errorCode = errorCode;
  }


  @Override
  public ExceptionResponse convert(MissingRequestHeaderException ex, int status) {
    return new ExceptionResponse(LocalDateTime.now(), errorType, errorCode, errorTitle, status,
            String.format(errorMessage, ex.getHeaderName()), null);
  }

  @Override
  public boolean supports(Exception exception) {
    Class clazz = MissingRequestHeaderException.class;
    return clazz.equals(exception.getClass());
  }
}
