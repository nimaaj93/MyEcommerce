package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductTagRepository extends JpaRepository<ProductTag, Long> {

    Optional<ProductTag> findByTagVal(String tagVal);

}
