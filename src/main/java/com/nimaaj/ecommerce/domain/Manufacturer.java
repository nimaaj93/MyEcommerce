package com.nimaaj.ecommerce.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "manufacturer")
public class Manufacturer {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titleEn;
    private String titleFa;

    @OneToMany(mappedBy = "manufacturer")
    private List<Product> products;

}
