package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.Customer;
import com.nimaaj.ecommerce.domain.CustomerAddress;
import com.nimaaj.ecommerce.dto.CustomerAddressDTO;
import com.nimaaj.ecommerce.enumaration.AddressStatus;
import com.nimaaj.ecommerce.exception.CustomerAddressNotFoundException;
import com.nimaaj.ecommerce.mapper.CustomerAddressMapper;
import com.nimaaj.ecommerce.repository.CustomerAddressRepository;
import com.nimaaj.ecommerce.repository.CustomerRepository;
import com.nimaaj.ecommerce.security.SecurityUtils;
import com.nimaaj.ecommerce.service.CustomerAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
@Validated
public class CustomerAddressServiceImpl implements CustomerAddressService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerAddressServiceImpl.class);

    private final CustomerAddressRepository customerAddressRepository;
    private final CustomerAddressMapper customerAddressMapper;

    public CustomerAddressServiceImpl(CustomerAddressRepository customerAddressRepository,
                                      CustomerAddressMapper customerAddressMapper) {
        this.customerAddressRepository = customerAddressRepository;
        this.customerAddressMapper = customerAddressMapper;
    }

    @Override
    public List<CustomerAddressDTO> getCustomerAddresses() {
        LOGGER.debug("getCustomerAddresses() called");
        Long customerId = SecurityUtils.getCurrentCustomerId().orElseThrow(() ->
             new IllegalStateException("No customerId found in user token!")
        );
        return customerAddressRepository.findAllByCustomer_IdAndAddressStatusNotIn(
                customerId, Arrays.asList(AddressStatus.DELETED))
                .stream()
                .map(customerAddressMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerAddressDTO getCustomerAddress(Long addressId) {
        LOGGER.debug("getCustomerAddress() called for {}", addressId);
        Long customerId = SecurityUtils.getCurrentCustomerId().orElseThrow(() ->
                new IllegalStateException("No customerId found in user token!")
        );
        CustomerAddress customerAddress = customerAddressRepository.findById(addressId)
                .filter(address -> address.getCustomer().getId().equals(customerId))
                .filter(address -> address.getAddressStatus() != AddressStatus.DELETED)
                .orElseThrow(CustomerAddressNotFoundException::new);
        return customerAddressMapper.toDto(customerAddress);
    }

    @Override
    public void deleteCustomerAddress(Long addressId) {
        LOGGER.debug("deleteCustomerAddress() called for {}", addressId);
        Long customerId = SecurityUtils.getCurrentCustomerId().orElseThrow(() ->
                new IllegalStateException("No customerId found in user token!")
        );
        CustomerAddress customerAddress = customerAddressRepository.findById(addressId)
                .filter(address -> address.getCustomer().getId().equals(customerId))
                .filter(address -> address.getAddressStatus() != AddressStatus.DELETED)
                .orElseThrow(CustomerAddressNotFoundException::new);
        customerAddress.setAddressStatus(AddressStatus.DELETED);
    }

    @Override
    public CustomerAddressDTO update(CustomerAddressDTO customerAddressDTO) {
        LOGGER.debug("update() called for {}", customerAddressDTO);
        Long addressId = customerAddressDTO.getId();
        Long customerId = SecurityUtils.getCurrentCustomerId().orElseThrow(() ->
                new IllegalStateException("No customerId found in user token!")
        );
        CustomerAddress customerAddress = customerAddressRepository.findById(addressId)
                .filter(address -> address.getCustomer().getId().equals(customerId))
                .filter(address -> address.getAddressStatus() != AddressStatus.DELETED)
                .orElseThrow(CustomerAddressNotFoundException::new);

        customerAddressMapper.toEntity(customerAddress,customerAddressDTO);
        return customerAddressMapper.toDto(customerAddressRepository.save(customerAddress));
    }

    @Override
    public CustomerAddressDTO add(CustomerAddressDTO customerAddressDTO) {
        LOGGER.debug("add() called for {}", customerAddressDTO);
        Long customerId = SecurityUtils.getCurrentCustomerId().orElseThrow(() ->
                new IllegalStateException("No customerId found in user token!")
        );
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setDefaultSelected(false);
        customerAddress.setCustomer(new Customer(customerId));
        customerAddress.setAddressStatus(AddressStatus.ACTIVE);
        return customerAddressMapper.toDto(customerAddressRepository.save(customerAddress));
    }

}