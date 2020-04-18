package com.nimaaj.ecommerce.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProductQaReplyDto {

    @NotNull
    private Long id;

    @Size(max = 1023)
    @NotBlank
    private String reply;

}
