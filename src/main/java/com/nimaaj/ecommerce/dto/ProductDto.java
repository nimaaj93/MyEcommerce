package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.ProductStatus;
import lombok.Data;

import java.util.List;

/**
 * Created by K550 VX on 27.09.2019.
 */
@Data
public class ProductDto {

    private Long id;
    private String code;
    private String titleEn;
    private String titleFa;
    private Long price;
    private ProductStatus status;
    private Integer stock;
    private boolean deliverable;

    private List<ProductMediaDto> productMediaList;
    private ProductDetailDto detail;
    private ProductCategoryDto category;

}