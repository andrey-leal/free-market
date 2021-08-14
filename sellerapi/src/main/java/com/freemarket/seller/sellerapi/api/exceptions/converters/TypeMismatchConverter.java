package com.freemarket.seller.sellerapi.api.exceptions.converters;

import com.freemarket.seller.sellerapi.api.exceptions.ExceptionResponse;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
class TypeMismatchConverter implements ExceptionConverterSupports<TypeMismatchException> {

    private final String errorCode;
    private final String errorTitle;
    private final String errorMessage;
    private final String errorType;

    public TypeMismatchConverter(
            @Value("${spring.validation.type_mismatch.code:invalid_type}") String errorCode,
            @Value("${spring.validation.type_mismatch.error:#{null}}") String errorTitle,
            @Value("${spring.validation.type_mismatch.message:Error converting attribute with value '%s'}") String errorMessage,
            @Value("${spring.validation.message_not_readable.type:/errors/invalid-data-type}") String errorType) {
        this.errorTitle = errorTitle;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.errorType = errorType;
    }

    @Override
    public ExceptionResponse convert(TypeMismatchException ex, int status) {
        return new ExceptionResponse(LocalDateTime.now(), errorType, errorCode, errorTitle, status,
                String.format(errorMessage, ex.getValue()), null);
    }

    @Override
    public boolean supports(Exception exception) {
        Class clazz = TypeMismatchException.class;
        return clazz.equals(exception.getClass());
    }
}
