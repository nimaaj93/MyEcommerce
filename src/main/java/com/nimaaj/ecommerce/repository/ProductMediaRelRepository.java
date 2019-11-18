package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.ProductMediaRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductMediaRelRepository extends JpaRepository<ProductMediaRel, Long> {

    Optional<ProductMediaRel> findTopByProduct_IdOrderByOrderVal(Long productId);

    Optional<ProductMediaRel> findByProduct_IdAndMainTrue(Long productId);

}