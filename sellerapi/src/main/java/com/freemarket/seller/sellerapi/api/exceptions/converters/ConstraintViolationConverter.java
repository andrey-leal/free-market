package com.freemarket.seller.sellerapi.api.exceptions.converters;


import com.freemarket.seller.sellerapi.api.exceptions.ExceptionDetail;
import com.freemarket.seller.sellerapi.api.exceptions.ExceptionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.hibernate.validator.internal.engine.path.PathImpl;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
class ConstraintViolationConverter implements ExceptionConverterSupports<ConstraintViolationException> {

  private final String errorCode;
  private final String errorTitle;
  private final String errorType;

  public ConstraintViolationConverter(
          @Value("${spring.validation.constraint_violation.code:validation_error}") String errorCode,
          @Value("${spring.validation.constraint_violation.error:Validation data error}") String errorTitle,
          @Value("${spring.validation.constraint_violation.type:/errors/validation-data}") String errorType) {
    this.errorTitle = errorTitle;
    this.errorCode = errorCode;
    this.errorType = errorType;
  }

  @Override
  public ExceptionResponse convert(ConstraintViolationException ex, int status) {
    List<ExceptionDetail> details =
        ex.getConstraintViolations().stream()
            .map(e -> new ExceptionDetail(((PathImpl) e.getPropertyPath()).getLeafNode().getName(), e.getMessage()))
            .collect(Collectors.toList());

    return new ExceptionResponse(LocalDateTime.now(), errorType, errorCode, errorTitle, status, details);
  }

  @Override
  public boolean supports(Exception exception) {
    Class clazz = ConstraintViolationException.class;
    return clazz.equals(exception.getClass());
  }

}
