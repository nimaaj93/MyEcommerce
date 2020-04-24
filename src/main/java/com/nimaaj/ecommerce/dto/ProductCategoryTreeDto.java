package com.nimaaj.ecommerce.dto;

import lombok.Data;

import java.util.List;


@Data
public class ProductCategoryTreeDto {

    private Long id;
    private String titleFa;
    private String titleEn;
    private String description;
    private Integer orderVal;
    private Long parentId;

    private List<ProductCategoryTreeDto> children;

}