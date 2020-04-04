package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.ProductStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "product")
@NamedEntityGraph(name = "Product.gridview",
        attributeNodes = {
        @NamedAttributeNode(value = "productMediaRels",
                subgraph = "ProductMediaRel.full"),
        @NamedAttributeNode(value = "detail")})
@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String titleEn;

    @NotNull
    private String titleFa;

    @NotNull
    private Long price;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @NotNull
    private Integer stock;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull
    private ProductCategory category;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductMediaRel> productMediaRels;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    private ProductDetail detail;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<UserBag> containingBags;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductTagRel> productTagRels;

    private boolean deliverable;

}