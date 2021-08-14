package com.freemarket.seller.sellerapi.api.exceptions.converters;

import com.freemarket.seller.sellerapi.api.exceptions.ExceptionResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Component
class MethodArgumentNotValidExceptionConverter implements ExceptionConverterSupports<MethodArgumentNotValidException> {

  private final BindingResultConverter bindingResultConverter;

  public MethodArgumentNotValidExceptionConverter(
      BindingResultConverter bindingResultConverter) {
    this.bindingResultConverter = bindingResultConverter;
  }

  @Override
  public ExceptionResponse convert(MethodArgumentNotValidException ex, int status) {
    return bindingResultConverter.convert(ex.getBindingResult(), status);
  }

  @Override
  public boolean supports(Exception exception) {
    Class clazz = MethodArgumentNotValidException.class;
    return clazz.equals(exception.getClass());
  }
}
