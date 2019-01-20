package com.nimaaj.ecommerce.domain;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titleEn;
    private String titleFa;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
