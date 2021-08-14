package com.freemarket.seller.sellerapi.api.exceptions.converters;

import com.freemarket.seller.sellerapi.api.exceptions.ExceptionDetail;
import com.freemarket.seller.sellerapi.api.exceptions.ExceptionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
class BindingResultConverter implements ExceptionConverterSupports<BindingResult> {

    private final String errorCode;
    private final String errorTitle;
    private final String errorType;
    private final ObjectErrorConverter objectErrorConverter;

    public BindingResultConverter(
            @Value("${spring.validation.constraint_violation.code:validation_error}") String errorCode,
            @Value("${spring.validation.constraint_violation.error:Validation data error}") String errorTitle,
            @Value("${spring.validation.constraint_violation.type:/errors/validation-data}") String errorType,
            ObjectErrorConverter objectErrorConverter) {
        this.errorTitle = errorTitle;
        this.errorType = errorType;
        this.errorCode = errorCode;
        this.objectErrorConverter = objectErrorConverter;
    }

    @Override
    public ExceptionResponse convert(BindingResult bindingResult, int status) {
        List<ExceptionDetail> details =
                bindingResult.getAllErrors().stream()
                        .map(objectErrorConverter::convert).collect(Collectors.toList());

        return new ExceptionResponse(LocalDateTime.now(), errorType, errorCode, errorTitle, status, details);
    }

    @Override
    public boolean supports(Exception exception) {
        Class clazz = BindException.class;
        return clazz.equals(exception.getClass());
    }
}
