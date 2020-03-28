package com.nimaaj.ecommerce.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "invoice")
@Data
@EqualsAndHashCode(callSuper = true)
public class Invoice extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Order order;

    @NotNull
    private Long totalItemsAmount;

    @NotNull
    private Long otherItemsAmount;

    @NotNull
    private Long totalAmount;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceRow> rows;

}
