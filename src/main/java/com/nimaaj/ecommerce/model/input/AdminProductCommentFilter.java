package com.nimaaj.ecommerce.model.input;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AdminProductCommentFilter {

    @Size(max = 255)
    private String q;
    @NotNull
    private Long productId;

}
