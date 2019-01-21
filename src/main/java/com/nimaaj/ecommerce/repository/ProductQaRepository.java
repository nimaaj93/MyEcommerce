package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.ProductQa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductQaRepository extends JpaRepository<ProductQa, Long> {
}
