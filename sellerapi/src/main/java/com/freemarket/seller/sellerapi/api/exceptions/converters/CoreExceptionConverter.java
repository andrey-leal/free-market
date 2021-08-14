package com.freemarket.seller.sellerapi.api.exceptions.converters;


import com.freemarket.seller.sellerapi.api.exceptions.CoreException;
import com.freemarket.seller.sellerapi.api.exceptions.ExceptionResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
class CoreExceptionConverter implements ExceptionConverterSupports<CoreException> {

  @Override
  public ExceptionResponse convert(CoreException ex, int status) {
    return new ExceptionResponse(LocalDateTime.now(), ex.getType(), ex.getCode(), ex.getTitle(), status, ex.getMessage(), null);
  }

  @Override
  public boolean supports(Exception exception) {
    return exception instanceof CoreException;
  }
}
