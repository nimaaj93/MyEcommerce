package com.nimaaj.ecommerce.model.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateProductCategoryModel {

    private Long id;
    @NotBlank
    private String titleFa;
    @NotBlank
    private String titleEn;
    private String description;
    private Long parentId;

}