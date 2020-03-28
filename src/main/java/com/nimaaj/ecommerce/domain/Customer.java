package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.CustomerType;
import com.nimaaj.ecommerce.enumaration.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String customerName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CustomerType customerType;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<CustomerAddress> addresses;


    public Customer() {
    }

    public Customer(Long id) {
        this.id = id;
    }

}
