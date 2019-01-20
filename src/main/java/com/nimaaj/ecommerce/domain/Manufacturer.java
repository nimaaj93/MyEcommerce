package com.nimaaj.ecommerce.domain;

import javax.persistence.*;

@Entity
@Table(name = "manufacturer")
public class Manufacturer {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titleEn;
    private String titleFa;

}
