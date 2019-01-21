package com.nimaaj.ecommerce.domain;

import javax.persistence.*;

@Entity
@Table(name = "product_category_attr_val")
public class ProductCategoryAttrVal extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ProductCategoryAttr attr;
    @ManyToOne
    private Product product;
    private String contentValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductCategoryAttr getAttr() {
        return attr;
    }

    public void setAttr(ProductCategoryAttr attr) {
        this.attr = attr;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getContentValue() {
        return contentValue;
    }

    public void setContentValue(String contentValue) {
        this.contentValue = contentValue;
    }
}
