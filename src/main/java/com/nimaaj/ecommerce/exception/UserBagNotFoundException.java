package com.nimaaj.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by K550 VX on 18.10.2019.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "user.bag.not.found")
public class UserBagNotFoundException extends BaseException {
}
