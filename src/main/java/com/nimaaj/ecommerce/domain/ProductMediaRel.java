package com.nimaaj.ecommerce.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "product_media_rel")
@NamedEntityGraph(name = "ProductMediaRel.full",
        attributeNodes = {
        @NamedAttributeNode("product"), @NamedAttributeNode("media")})
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductMediaRel extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer orderVal;
    private boolean main;

    @ManyToOne
    private Product product;
    @ManyToOne
    private Media media;


}
