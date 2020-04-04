package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.CommentStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product_comment")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductComment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 511)
    private String comment;

    @ManyToOne
    @NotNull
    private User user;

    @ManyToOne
    @NotNull
    private Product product;

    @ManyToOne
    private ProductComment parent;

    private boolean edited;

    private boolean adminComment;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CommentStatus status;

}
