package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.CustomerAddressDto;

import java.util.List;

public interface CustomerAddressService {

    List<CustomerAddressDto> getCustomerAddresses();

    CustomerAddressDto getCustomerAddress(Long addressId);

    void deleteCustomerAddress(Long addressId);

    CustomerAddressDto update(CustomerAddressDto customerAddressDTO);

    CustomerAddressDto add(CustomerAddressDto customerAddressDTO);

}