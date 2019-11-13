package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.ProductCategoryAttrType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductCategoryAttrDTO {

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getTitleFa() {
        return titleFa;
    }

    public void setTitleFa(String titleFa) {
        this.titleFa = titleFa;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public ProductCategoryAttrType getAttrType() {
        return attrType;
    }

    public void setAttrType(ProductCategoryAttrType attrType) {
        this.attrType = attrType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    @Override
    public String toString() {
        return "ProductCategoryAttrDTO{" +
                "id=" + id +
                ", titleEn='" + titleEn + '\'' +
                ", titleFa='" + titleFa + '\'' +
                ", description='" + description + '\'' +
                ", required=" + required +
                ", attrType=" + attrType +
                ", groupId='" + groupId + '\'' +
                ", productCategoryId=" + productCategoryId +
                '}';
    }

    // validation groups
    public static class Create {
    }

    public static class Update {
    }

}