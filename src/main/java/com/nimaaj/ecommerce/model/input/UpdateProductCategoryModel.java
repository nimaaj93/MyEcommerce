package com.nimaaj.ecommerce.model.input;

import javax.validation.constraints.NotBlank;

public class UpdateProductCategoryModel {

    private Long id;
    @NotBlank
    private String titleFa;
    @NotBlank
    private String titleEn;
    private String description;
    private Long parentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return "UpdateProductCategoryModel{" +
                "id=" + id +
                ", titleFa='" + titleFa + '\'' +
                ", titleEn='" + titleEn + '\'' +
                ", description='" + description + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}