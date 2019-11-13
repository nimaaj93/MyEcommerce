package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.ProductCategoryAttrType;

import javax.persistence.*;

@Entity
@Table(name = "product_category_attr")
public class ProductCategoryAttr extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titleEn;
    private String titleFa;
    private String description;
    private boolean required;
    private ProductCategoryAttrType attrType;
    private String groupId;
    @ManyToOne
    private ProductCategory productCategory;


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

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
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
}
