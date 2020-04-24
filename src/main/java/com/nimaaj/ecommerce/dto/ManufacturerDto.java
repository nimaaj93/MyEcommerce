package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.ManufacturerStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class ManufacturerDto {

    @NotNull(groups = { Update.class })
    @Null(groups = { Create.class })
    private Long id;
    @NotNull(groups = { Create.class, Update.class })
    private String titleEn;
    @NotNull(groups = { Create.class, Update.class })
    private String titleFa;
    private String details;
    @NotNull(groups = { Create.class, Update.class })
    private ManufacturerStatus manufacturerStatus;


    // validation groups
    public class Create {
    }

    public class Update {
    }

}