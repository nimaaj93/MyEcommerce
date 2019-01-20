package com.nimaaj.ecommerce.domain;

import javax.persistence.*;

@Entity
@Table(name = "invoice")
public class Invoice extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Order order;

}
