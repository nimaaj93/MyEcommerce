package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.ProductCategoryAttrType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "product_category_attr")
@Data
@EqualsAndHashCode(callSuper = true)
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

}
