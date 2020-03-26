package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.OrderPayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderPaymentRepository extends JpaRepository<OrderPayment, Long> {

    Optional<OrderPayment> findByRequestId(String requestId);

    Page<OrderPayment> findAllByUser_Id(Pageable pageable, Long userId);

}
