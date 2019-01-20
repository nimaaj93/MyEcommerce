package com.nimaaj.ecommerce.domain;

import javax.persistence.*;

@Entity
@Table(name = "customer_address")
public class CustomerAddress extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cityFa;
    private String cityEn;
    private String addressFa;
    private String addressEn;
    private String postCode;
    private boolean defaultSelected;

    @ManyToOne
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityFa() {
        return cityFa;
    }

    public void setCityFa(String cityFa) {
        this.cityFa = cityFa;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getAddressFa() {
        return addressFa;
    }

    public void setAddressFa(String addressFa) {
        this.addressFa = addressFa;
    }

    public String getAddressEn() {
        return addressEn;
    }

    public void setAddressEn(String addressEn) {
        this.addressEn = addressEn;
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
}
