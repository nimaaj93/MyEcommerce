package com.nimaaj.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "unknown.system.exception")
public class UnknownSystemException extends BaseException {

    public UnknownSystemException() {
    }

    public UnknownSystemException(String message) {
        super(message);
    }

    public UnknownSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownSystemException(Throwable cause) {
        super(cause);
    }
}