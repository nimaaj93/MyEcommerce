package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.OtpRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRequestRepository extends JpaRepository<OtpRequest, Long> {

    Optional<OtpRequest> findTopByUser_IdAndUsedIsFalseOrderByCreateDateTimeDesc(Long userId);

}