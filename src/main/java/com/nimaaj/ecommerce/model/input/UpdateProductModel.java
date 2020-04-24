package com.nimaaj.ecommerce.model.input;

import com.nimaaj.ecommerce.enumaration.ProductStatus;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by K550 VX on 27.10.2019.
 */
@Data
public class UpdateProductModel {

    @NotBlank
    private String titleEn;
    @NotBlank
    private String titleFa;
    @NotNull
    @Min(value = 0L)
    private Long price;
    @NotNull
    @Min(value = 0L)
    private Long manufacturerId;
    @NotNull
    @Min(value = 0L)
    private Integer stock;
    @NotNull
    private ProductStatus status;
    @NotNull
    @Min(value = 0L)
    private Long categoryId;
    private String details;

}