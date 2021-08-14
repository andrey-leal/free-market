package com.freemarket.seller.sellerapi.api.exceptions.converters;

import com.freemarket.seller.sellerapi.api.exceptions.ExceptionDetail;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Component
public class ObjectErrorConverter {

    public ExceptionDetail convert(ObjectError objectError) {
        if (objectError instanceof FieldError) {
            return convertFieldError((FieldError) objectError);
        }
        return convertObjectError(objectError);
    }

    private ExceptionDetail convertObjectError(ObjectError objectError) {
        return new ExceptionDetail(objectError.getObjectName(), objectError.getDefaultMessage());
    }

    private ExceptionDetail convertFieldError(FieldError fieldError) {
        return new ExceptionDetail(fieldError.getField(), fieldError.getDefaultMessage());

    }

}
