package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.SaleStatus;
import com.nimaaj.ecommerce.enumaration.SaleType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class SaleDto {

    @NotNull
    @Null
    private Long id;

    @NotBlank
    private String description;

    @NotNull
    private SaleType saleType;

    private String saleRefVal;

    @NotNull
    private SaleStatus saleStatus;

    private Long startDateTime;

    private Long endDateTime;

}
