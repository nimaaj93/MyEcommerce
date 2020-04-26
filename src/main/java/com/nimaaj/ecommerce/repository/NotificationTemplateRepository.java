package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.NotificationTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplate, Long> {

    Optional<NotificationTemplate> findByTemplateCode(String code);

    Page<NotificationTemplate> findAll(Specification<NotificationTemplate> specification,
                                        Pageable pageable);

}