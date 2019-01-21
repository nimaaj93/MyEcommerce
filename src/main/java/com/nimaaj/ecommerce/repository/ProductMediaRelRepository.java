package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.ProductMediaRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMediaRelRepository extends JpaRepository<ProductMediaRel, Long> {
}
