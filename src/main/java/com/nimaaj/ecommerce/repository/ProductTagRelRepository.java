package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.ProductTagRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductTagRelRepository extends JpaRepository<ProductTagRel, Long> {

    Optional<ProductTagRel> findByProduct_IdAndProductTag_Id(Long productId, Long productTagId);

}
