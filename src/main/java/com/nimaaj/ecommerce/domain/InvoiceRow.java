package com.nimaaj.ecommerce.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "invoice_row")
@Data
@EqualsAndHashCode(callSuper = true)
public class InvoiceRow extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String title;

    private String decription;

    @NotNull
    private Long amount;

    @ManyToOne
    private OrderItem orderItem;

    @ManyToOne
    @NotNull
    private Invoice invoice;

}
