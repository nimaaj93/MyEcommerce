package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.CustomerAddress;
import com.nimaaj.ecommerce.enumaration.AddressStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {

    List<CustomerAddress> findAllByCustomer_IdAndAddressStatusNotIn(
            Long customerId, List<AddressStatus> addressStatuses);

}