package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.ProductQuantityDivision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductQuantityDivisionRepository extends JpaRepository<ProductQuantityDivision, Long> {

    List<ProductQuantityDivision> findAllByProduct_Id(Long productId);

    @Modifying(clearAutomatically = true)
    @Query(" UPDATE ProductQuantityDivision pqd SET pqd.quantity = pqd.quantity + :increment WHERE pqd.id = :id ")
    int increaseQuantity(@Param("id") Long id, @Param("increment") Integer increment);

    @Modifying(clearAutomatically = true)
    @Query(" UPDATE ProductQuantityDivision pqd SET pqd.quantity = pqd.quantity - :decrement WHERE pqd.id = :id ")
    int decreaseQuantity(@Param("id") Long id, @Param("decrement") Integer decrement);

}
