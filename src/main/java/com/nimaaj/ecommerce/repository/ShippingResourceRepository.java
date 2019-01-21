package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.ShippingResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingResourceRepository extends JpaRepository<ShippingResource, Long> {
}
