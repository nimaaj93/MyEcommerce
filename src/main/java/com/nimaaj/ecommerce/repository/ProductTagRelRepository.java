package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.ProductTagRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTagRelRepository extends JpaRepository<ProductTagRel, Long> {
}
