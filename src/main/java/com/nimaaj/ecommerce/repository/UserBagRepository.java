package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.UserBag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserBagRepository extends JpaRepository<UserBag, Long> {

    List<UserBag> findByUser_id(Long userId);

    Optional<UserBag> findByProduct_IdAndUser_Id(Long productId, Long userId);

}