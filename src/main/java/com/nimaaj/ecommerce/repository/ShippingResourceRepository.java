package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.ShippingResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShippingResourceRepository extends JpaRepository<ShippingResource, Long> {

    List<ShippingResource> findAllByStartDateTimeBeforeAndEndDateTimeAfter(Date fromDate, Date toDate);

}