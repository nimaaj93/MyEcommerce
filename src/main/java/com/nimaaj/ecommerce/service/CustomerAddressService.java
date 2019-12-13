package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.domain.CustomerAddress;
import com.nimaaj.ecommerce.dto.CustomerAddressDTO;

import java.util.List;

public interface CustomerAddressService {

    List<CustomerAddressDTO> getCustomerAddresses();

    CustomerAddressDTO getCustomerAddress(Long addressId);

    void deleteCustomerAddress(Long addressId);

    CustomerAddressDTO update(CustomerAddressDTO customerAddressDTO);

    CustomerAddressDTO add(CustomerAddressDTO customerAddressDTO);

}