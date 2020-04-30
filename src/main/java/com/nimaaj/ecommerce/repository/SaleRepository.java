package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.Sale;
import com.nimaaj.ecommerce.enumaration.SaleStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    Page<Sale> findAll(Specification<Sale> specification, Pageable pageable);

    List<Sale> findAllBySaleStatus(SaleStatus saleStatus);

}
