package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.CustomerType;


public class CustomerDTO {

    private Long id;
    private String customerName;
    private CustomerType customerType;

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
}