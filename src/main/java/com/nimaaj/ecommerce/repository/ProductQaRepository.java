package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.ProductQa;
import com.nimaaj.ecommerce.enumaration.ProductQaStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductQaRepository extends JpaRepository<ProductQa, Long> {

    Page<ProductQa> findAllByStatusInAndProduct_Id(Pageable pageable,
                                                   List<ProductQaStatus> statuses,
                                                   Long productId);

}
