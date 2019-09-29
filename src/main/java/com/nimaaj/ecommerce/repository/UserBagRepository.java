package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.UserBag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBagRepository extends JpaRepository<UserBag, Long> {

    List<UserBag> findAllByUser_Id(Long id);

}