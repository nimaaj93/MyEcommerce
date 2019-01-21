package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.OrderShipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderShippingRepository extends JpaRepository<OrderShipping, Long> {
}
