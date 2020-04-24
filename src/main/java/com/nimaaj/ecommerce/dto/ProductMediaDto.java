package com.nimaaj.ecommerce.dto;

import lombok.Data;

/**
 * Created by K550 VX on 27.09.2019.
 */
@Data
public class ProductMediaDto {

    private Long id;
    private Long productId;
    private Integer orderVal;
    private boolean main;
    private MediaDto media;

}
