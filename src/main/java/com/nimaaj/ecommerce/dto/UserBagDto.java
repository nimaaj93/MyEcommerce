package com.nimaaj.ecommerce.dto;

import lombok.Data;

/**
 * Created by K550 VX on 27.09.2019.
 */
@Data
public class UserBagDto {

    private Long id;
    private Long productId;
    private Long userId;
    private Integer count;
    private Long expireDateTime;

}