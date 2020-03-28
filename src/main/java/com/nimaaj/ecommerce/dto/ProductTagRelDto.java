package com.nimaaj.ecommerce.dto;

import lombok.Data;

@Data
public class ProductTagRelDto {

    private Long id;
    private ProductTagDto productTag;
    private ProductDto product;

}
