package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {

    @EntityGraph(value = "Product.gridview", type = EntityGraph.EntityGraphType.LOAD)
    Page<Product> findAll(Specification<Product> specification, Pageable pageable);

    Optional<Product> findByCode(String code);

    List<Product> findAllByIdIn(Set<Long> ids);

}
