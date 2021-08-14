package com.freemarket.seller.sellerapi.api.exceptions;

import com.freemarket.seller.sellerapi.api.exceptions.converters.ExceptionConverter;
import com.freemarket.seller.sellerapi.api.exceptions.extractors.ResponseStatusExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class DefaultResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory
            .getLogger(DefaultResponseEntityExceptionHandler.class);

    private final ExceptionConverter exceptionConverter;
    private final ResponseStatusExtractor responseStatusExtractor;

    public DefaultResponseEntityExceptionHandler(
            ExceptionConverter exceptionConverter,
            ResponseStatusExtractor responseStatusExtractor) {
        this.exceptionConverter = exceptionConverter;
        this.responseStatusExtractor = responseStatusExtractor;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllException(Exception ex) {
        LOG.debug("Handling generic exception", ex);
        HttpStatus responseStatus = responseStatusExtractor.extract(ex).orElse(INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(responseStatus)
                .body(exceptionConverter.convert(ex, responseStatus.value()));
    }

    @ExceptionHandler(CoreException.class)
    public ResponseEntity<ExceptionResponse> handleCoreException(CoreException ex) {
        LOG.debug("Handling CoreException", ex);
        HttpStatus responseStatus = responseStatusExtractor.extract(ex).orElse(INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(responseStatus).body(exceptionConverter.convert(ex, responseStatus.value()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionResponse> handle(ConstraintViolationException ex) {
        LOG.debug("Handling ConstraintViolationException", ex);
        return ResponseEntity.badRequest().body(exceptionConverter.convert(ex, HttpStatus.BAD_REQUEST.value()));
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOG.debug("Handling TypeMismatchException", ex);
        return ResponseEntity.status(status).body(exceptionConverter.convert(ex, status.value()));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOG.debug("Handling HttpMessageNotReadableException", ex);
        return ResponseEntity.status(status).body(exceptionConverter.convert(ex, status.value()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOG.debug("Handling MethodArgumentNotValidException", ex);
        return ResponseEntity.status(status)
                .body(exceptionConverter.convert(ex, status.value()));
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers,
                                                         HttpStatus status, WebRequest request) {
        LOG.debug("Handling BindException", ex);
        return ResponseEntity.status(status).body(exceptionConverter.convert(ex, status.value()));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        LOG.debug("Handling MissingServletRequestParameterException", ex);
        return ResponseEntity.status(status).body(exceptionConverter.convert(ex, status.value()));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body,
                                                             HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            LOG.debug(
                    "Putting a body for the non overriden methods of org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler");
            LOG.debug("Exception being converted", ex);
            body = exceptionConverter.convert(ex, status.value());
            LOG.debug("Generated body {}.", body);
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

}