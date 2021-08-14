package com.freemarket.seller.sellerapi.api.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {

    private LocalDateTime timestamp;

    private String type;

    private String code;

    private String title;

    private int status;

    private String message;

    private List<ExceptionDetail> details;

    public ExceptionResponse(LocalDateTime timestamp, String type, String code, String title, int status, List<ExceptionDetail> details) {
        this.timestamp = timestamp;
        this.type = type;
        this.code = code;
        this.title = title;
        this.status = status;
        this.details = details;
    }

    public ExceptionResponse(LocalDateTime timestamp, String type, String code, String title, int status, String message, List<ExceptionDetail> details) {
        this.timestamp = timestamp;
        this.type = type;
        this.code = code;
        this.title = title;
        this.status = status;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<ExceptionDetail> getDetails() {
        return details;
    }
}
