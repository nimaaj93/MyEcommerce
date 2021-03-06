package com.nimaaj.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "unsupported.update.for.comment.state")
public class UnsuppertedUpdateForCommentStateException extends BaseException {
}
