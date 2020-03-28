package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.AddressStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customer_address")
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerAddress extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String city;

    @NotBlank
    @NotNull
    private String addressVal;

    @NotBlank
    @NotNull
    private String postCode;

    private boolean defaultSelected;

    @Enumerated(EnumType.STRING)
    @NotNull
    private AddressStatus addressStatus;

    @ManyToOne
    @NotNull
    private Customer customer;


}
