package com.nimaaj.ecommerce.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;

@Data
public class ShippingResourceDto {

    @Null(groups = { Create.class })
    @NotNull(groups = { Update.class })
    private Long id;
    @NotNull(groups = { Create.class, Update.class })
    private Long startDateTime;
    @NotNull(groups = { Create.class, Update.class })
    private Long endDateTime;
    @NotNull(groups = { Create.class, Update.class })
    @PositiveOrZero(groups = { Create.class, Update.class })
    private Integer total;
    @NotNull(groups = { Create.class, Update.class })
    @PositiveOrZero(groups = { Create.class, Update.class })
    private Integer available;

    // validation groups
    public class Create {
    }

    public class Update {
    }

}