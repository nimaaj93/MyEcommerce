package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.ProductCategoryAttrVal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryAttrValRepository extends JpaRepository<ProductCategoryAttrVal, Long> {
}
