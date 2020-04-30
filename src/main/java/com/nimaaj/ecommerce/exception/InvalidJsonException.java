package com.nimaaj.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "invalid.json")
public class InvalidJsonException extends BaseException {

    public InvalidJsonException(String message, Throwable cause) {
        super(message, cause);
    }
}
