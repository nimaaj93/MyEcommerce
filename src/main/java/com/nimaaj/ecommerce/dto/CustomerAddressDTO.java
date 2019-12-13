package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.AddressStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

public class CustomerAddressDTO {

    @NotNull(groups = { Update.class })
    @Null(groups = { Create.class })
    private Long id;
    @NotBlank(groups = { Create.class, Update.class })
    @Size(max = 40, groups = { Create.class, Update.class })
    private String city;
    @NotBlank(groups = { Create.class, Update.class })
    @Size(max = 256, groups = { Create.class, Update.class })
    private String addressVal;
    @NotBlank(groups = { Create.class, Update.class })
    @Size(max = 20, groups = { Create.class, Update.class })
    private String postCode;
    private boolean defaultSelected;
    @Null(groups = { Create.class, Update.class })
    private AddressStatus addressStatus;
    @Null(groups = { Create.class, Update.class })
    private Long customerId;

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

    public AddressStatus getAddressStatus() {
        return addressStatus;
    }

    public void setAddressStatus(AddressStatus addressStatus) {
        this.addressStatus = addressStatus;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    // validation groups
    public class Create {
    }
    public class Update {
    }

    @Override
    public String toString() {
        return "CustomerAddressDTO{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", addressVal='" + addressVal + '\'' +
                ", postCode='" + postCode + '\'' +
                ", defaultSelected=" + defaultSelected +
                ", addressStatus=" + addressStatus +
                ", customerId=" + customerId +
                '}';
    }
}