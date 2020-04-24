package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.ProductCategoryAttrType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductCategoryAttrDto {

    @NotNull(groups = { Update.class })
    private Long id;
    @NotBlank(groups = { Update.class, Create.class })
    private String titleEn;
    @NotBlank(groups = { Update.class, Create.class })
    private String titleFa;
    @NotBlank(groups = { Update.class, Create.class })
    private String description;
    private boolean required;
    @NotNull(groups = { Update.class, Create.class })
    private ProductCategoryAttrType attrType;
    @NotBlank(groups = { Update.class, Create.class })
    private String groupId;
    @NotNull(groups = { Update.class, Create.class })
    private Long productCategoryId;


    // validation groups
    public static class Create {
    }

    public static class Update {
    }

}