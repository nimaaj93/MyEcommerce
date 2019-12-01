package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.ResetPasswordRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResetPasswordRequestRepository extends JpaRepository<ResetPasswordRequest, Long> {

    List<ResetPasswordRequest> findAllByUser_Id(Long userId);

    Optional<ResetPasswordRequest> findOneByRequestCode(String code);

}