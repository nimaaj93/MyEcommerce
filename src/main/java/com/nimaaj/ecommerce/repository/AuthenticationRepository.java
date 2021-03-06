package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {

    Authentication findByUser_MobileNumber(String mobileNumber);

    Authentication findByUser_Email(String email);

}
