package com.nimaaj.ecommerce.model.input;

import javax.validation.constraints.NotBlank;

public class AddProductCategoryModel {

    @NotBlank
    private String titleFa;
    @NotBlank
    private String titleEn;
    private String description;
    private Long parentId;

    public String getTitleFa() {
        return titleFa;
    }

    public void setTitleFa(String titleFa) {
        this.titleFa = titleFa;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "AddProductCategoryModel{" +
                "titleFa='" + titleFa + '\'' +
                ", titleEn='" + titleEn + '\'' +
                ", description='" + description + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}