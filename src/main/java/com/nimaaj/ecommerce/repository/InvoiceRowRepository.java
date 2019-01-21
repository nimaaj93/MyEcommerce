package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.InvoiceRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRowRepository extends JpaRepository<InvoiceRow, Long> {
}
