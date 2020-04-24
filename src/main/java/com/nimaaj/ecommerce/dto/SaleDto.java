package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.SaleStatus;
import com.nimaaj.ecommerce.enumaration.SaleType;
import lombok.Data;

@Data
public class SaleDto {

    private Long id;
    private SaleType saleType;
    private String saleRefVal;
    private SaleStatus saleStatus;
    private Long startDateTime;
    private Long endDateTime;

}
