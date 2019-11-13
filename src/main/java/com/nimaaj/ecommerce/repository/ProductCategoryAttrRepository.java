package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.ProductCategoryAttr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryAttrRepository extends JpaRepository<ProductCategoryAttr, Long> {

    List<ProductCategoryAttr> findAllByProductCategory_Id(Long productCategoryId);

}