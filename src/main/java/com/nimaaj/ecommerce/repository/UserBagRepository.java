package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.UserBag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBagRepository extends JpaRepository<UserBag, Long> {
}
