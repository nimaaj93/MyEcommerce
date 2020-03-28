package com.nimaaj.ecommerce.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "product_category_attr_val")
@Data
@EqualsAndHashCode(callSuper = true)
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

}
