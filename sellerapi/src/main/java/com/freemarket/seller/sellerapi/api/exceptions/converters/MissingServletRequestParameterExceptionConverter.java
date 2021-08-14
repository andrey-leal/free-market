package com.freemarket.seller.sellerapi.api.exceptions.converters;

import com.freemarket.seller.sellerapi.api.exceptions.ExceptionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.time.LocalDateTime;

@Component
class MissingServletRequestParameterExceptionConverter implements ExceptionConverterSupports<MissingServletRequestParameterException> {

  private final String errorCode;
  private final String errorTitle;
  private final String errorMessage;
  private final String errorType;

  public MissingServletRequestParameterExceptionConverter(
          @Value("${spring.validation.parameter_missing.code:missing_parameter}") String errorCode,
          @Value("${spring.validation.parameter_missing.error:#{null}}") String errorTitle,
          @Value("${spring.validation.parameter_missing.message:Required parameter '%s' not sent}") String errorMessage,
          @Value("${spring.validation.message_not_readable.type:/errors/invalid-call}") String errorType) {
    this.errorTitle = errorTitle;
    this.errorMessage = errorMessage;
    this.errorCode = errorCode;
    this.errorType = errorType;
  }

  @Override
  public ExceptionResponse convert(MissingServletRequestParameterException ex, int status) {
    return new ExceptionResponse(LocalDateTime.now(), errorType, errorCode, errorTitle, status,
            String.format(errorMessage, ex.getParameterName()), null);
  }

  @Override
  public boolean supports(Exception exception) {
    Class clazz = MissingServletRequestParameterException.class;
    return clazz.equals(exception.getClass());
  }
}
