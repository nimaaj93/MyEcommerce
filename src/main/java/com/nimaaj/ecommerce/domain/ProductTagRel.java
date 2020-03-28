package com.nimaaj.ecommerce.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "product_tag_rel")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductTagRel extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private ProductTag productTag;
    @ManyToOne
    private Product product;

}
