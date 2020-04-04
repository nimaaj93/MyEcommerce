package com.nimaaj.ecommerce.repository;

import com.nimaaj.ecommerce.domain.ProductComment;
import com.nimaaj.ecommerce.enumaration.CommentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {

    Page<ProductComment> findAllByProduct_IdAndStatusIn(Long productId, List<CommentStatus> commentStatusList, Pageable pageable);

    Page<ProductComment> findAll(Specification<ProductComment> specification, Pageable pageable);

}
