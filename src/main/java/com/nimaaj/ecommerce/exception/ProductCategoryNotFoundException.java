package com.nimaaj.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by K550 VX on 3/5/2019.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "product.not.found")
public class ProductCategoryNotFoundException extends BaseException {
}
