package com.nimaaj.ecommerce.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "product_category")
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titleFa;
    private String titleEn;
    private String description;
    private Integer orderVal;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductCategory parent;
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<ProductCategory> children;
    @OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY)
    private List<ProductCategoryAttr> productCategoryAttrs;

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

    public ProductCategory getParent() {
        return parent;
    }

    public void setParent(ProductCategory parent) {
        this.parent = parent;
    }

    public List<ProductCategory> getChildren() {
        return children;
    }

    public void setChildren(List<ProductCategory> children) {
        this.children = children;
    }

    public Integer getOrderVal() {
        return orderVal;
    }

    public void setOrderVal(Integer orderVal) {
        this.orderVal = orderVal;
    }

    public List<ProductCategoryAttr> getProductCategoryAttrs() {
        return productCategoryAttrs;
    }

    public void setProductCategoryAttrs(List<ProductCategoryAttr> productCategoryAttrs) {
        this.productCategoryAttrs = productCategoryAttrs;
    }
}
