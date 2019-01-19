package com.nimaaj.ecommerce.domain;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
