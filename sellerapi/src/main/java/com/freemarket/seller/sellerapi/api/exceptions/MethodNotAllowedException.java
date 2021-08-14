package com.freemarket.seller.sellerapi.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class MethodNotAllowedException extends CoreException {

	public MethodNotAllowedException(String message, String type, String code, String title) {
		super(message, type, code, title);
	}

	public MethodNotAllowedException(String message, Throwable cause, String type, String code, String title) {
		super(message, cause, type, code, title);
	}

	public MethodNotAllowedException(Throwable cause, String type, String code, String title) {
		super(cause, type, code, title);
	}

	public MethodNotAllowedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String type, String code, String title) {
		super(message, cause, enableSuppression, writableStackTrace, type, code, title);
	}
}
