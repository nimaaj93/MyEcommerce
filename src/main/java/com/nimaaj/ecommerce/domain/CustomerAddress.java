package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.AddressStatus;

import javax.persistence.*;

@Entity
@Table(name = "customer_address")
public class CustomerAddress extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String addressVal;
    private String postCode;
    private boolean defaultSelected;
    @Enumerated(EnumType.STRING)
    private AddressStatus addressStatus;

    @ManyToOne
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressVal() {
        return addressVal;
    }

    public void setAddressVal(String addressVal) {
        this.addressVal = addressVal;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public boolean isDefaultSelected() {
        return defaultSelected;
    }

    public void setDefaultSelected(boolean defaultSelected) {
        this.defaultSelected = defaultSelected;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AddressStatus getAddressStatus() {
        return addressStatus;
    }

    public void setAddressStatus(AddressStatus addressStatus) {
        this.addressStatus = addressStatus;
    }
}
