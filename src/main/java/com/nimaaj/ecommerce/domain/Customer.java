package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.CustomerType;
import com.nimaaj.ecommerce.enumaration.Gender;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<User> users;
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<CustomerAddress> addresses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<CustomerAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<CustomerAddress> addresses) {
        this.addresses = addresses;
    }
}
