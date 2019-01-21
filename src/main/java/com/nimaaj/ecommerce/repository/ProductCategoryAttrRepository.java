package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.ProductCategoryAttr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryAttrRepository extends JpaRepository<ProductCategoryAttr, Long> {
}
