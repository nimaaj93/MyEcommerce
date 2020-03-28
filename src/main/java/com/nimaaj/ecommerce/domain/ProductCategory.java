package com.nimaaj.ecommerce.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "product_category")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductCategory extends BaseEntity {

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

}