package com.nimaaj.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by K550 VX on 27.10.2019.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "product.not.found")
public class ProductNotFoundException extends BaseException {
}