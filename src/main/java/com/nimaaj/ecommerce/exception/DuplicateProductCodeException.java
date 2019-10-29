package com.nimaaj.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by K550 VX on 27.10.2019.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "duplicate.product.code")
public class DuplicateProductCodeException extends BaseException {
}