package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.DiscountCodeState;
import com.nimaaj.ecommerce.enumaration.DiscountCodeType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class DiscountCodeDto {

    @NotNull(groups = { Update.class })
    @Null(groups = { Create.class })
    private Long id;
    @NotBlank(groups = { Create.class, Update.class })
    private String title;
    @Null(groups = { Update.class })
    private String codeVal;
    private String description;
    @NotNull(groups = { Create.class, Update.class })
    private DiscountCodeType discountCodeType;
    private String discountRefVal;
    private Long maxAmount;
    private Long expireDateTime;
    @NotNull(groups = { Create.class })
    @Null(groups = { Update.class })
    private DiscountCodeState state;

    // validation groups
    public class Create {
    }

    public class Update {
    }

}