package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneWithAuthoritiesByEmail(String email);

    Optional<User> findOneWithAuthoritiesByMobileNumber(String mobileNumber);

    Optional<User> findOneWithAuthoritiesByMobileNumberOrEmail(String mobileNumber, String email);

    Page<User> findAll(Specification<User> specification, Pageable pageable);

}