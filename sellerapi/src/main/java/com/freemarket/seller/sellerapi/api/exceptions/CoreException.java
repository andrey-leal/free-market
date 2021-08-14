package com.freemarket.seller.sellerapi.api.exceptions;

public class CoreException extends RuntimeException {

    private String type;

    private String code;

    private String title;


    public CoreException(String message, String type, String code, String title) {
        super(message);
        this.type = type;
        this.code = code;
        this.title = title;
    }

    public CoreException(String message, Throwable cause, String type, String code, String title) {
        super(message, cause);
        this.type = type;
        this.code = code;
        this.title = title;
    }

    public CoreException(Throwable cause, String type, String code, String title) {
        super(cause);
        this.type = type;
        this.code = code;
        this.title = title;
    }

    public CoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
                         String type, String code, String title) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.type = type;
        this.code = code;
        this.title = title;
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
}
