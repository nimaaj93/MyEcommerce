package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    List<ProductCategory> findAllByParent_Id(Long id);

    List<ProductCategory> findAllByParentIsNull();

    Optional<Integer> findTopByParent_IdOrderByOrderValDesc(Long id);

}
