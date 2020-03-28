package com.nimaaj.ecommerce.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "product_comment")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductComment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;
    @ManyToOne
    private ProductComment parent;
    private boolean edited;

}
