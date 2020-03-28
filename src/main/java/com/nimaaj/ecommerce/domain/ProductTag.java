package com.nimaaj.ecommerce.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product_tag",
       uniqueConstraints = @UniqueConstraint(name = "product_tag_val_uq", columnNames = "tagVal"))
public class ProductTag extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 255)
    private String tagVal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagVal() {
        return tagVal;
    }

    public void setTagVal(String tagVal) {
        this.tagVal = tagVal;
    }
}
