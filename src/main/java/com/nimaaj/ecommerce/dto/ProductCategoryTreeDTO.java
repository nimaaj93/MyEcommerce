package com.nimaaj.ecommerce.dto;

import java.util.List;

public class ProductCategoryTreeDTO {

    private Long id;
    private String titleFa;
    private String titleEn;
    private String description;
    private Integer orderVal;
    private Long parentId;

    private List<ProductCategoryTreeDTO> children;

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

    public Integer getOrderVal() {
        return orderVal;
    }

    public void setOrderVal(Integer orderVal) {
        this.orderVal = orderVal;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<ProductCategoryTreeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ProductCategoryTreeDTO> children) {
        this.children = children;
    }
}