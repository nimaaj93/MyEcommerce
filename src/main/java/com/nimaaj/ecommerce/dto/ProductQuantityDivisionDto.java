package com.nimaaj.ecommerce.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;

@Data
public class ProductQuantityDivisionDto {

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    private Long id;

    @NotEmpty(groups = { Create.class, Update.class })
    private ArrayList<ProductQuantityDivisionCriteriaDto> criteria;

    @PositiveOrZero(groups = { Create.class, Update.class })
    private Integer quantity;

    @Null(groups = { Create.class, Update.class })
    private Long productId;

    // validation groups
    public class Create {
    }

    public class Update {
    }

}
