package com.nimaaj.ecommerce.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ProductNewQuestionDto {

    @Size(max = 511)
    @NotBlank
    private String question;

}
