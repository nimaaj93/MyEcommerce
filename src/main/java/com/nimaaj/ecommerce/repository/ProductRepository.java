package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {

    @EntityGraph(value = "Product.gridview", type = EntityGraph.EntityGraphType.LOAD)
    Page<Product> findAll(Specification<Product> specification, Pageable pageable);

}
