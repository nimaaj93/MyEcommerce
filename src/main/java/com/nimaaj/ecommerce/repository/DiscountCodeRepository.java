package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.DiscountCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long> {

    Optional<DiscountCode> findByCodeVal(String code);

    Page<DiscountCode> findAll(Specification<DiscountCode> specification, Pageable pageable);

}