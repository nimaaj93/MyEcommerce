package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.ProductCommentDto;
import com.nimaaj.ecommerce.model.input.AdminProductCommentFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductCommentService {

    ProductCommentDto addComment(ProductCommentDto productCommentDto);

    ProductCommentDto updateComment(ProductCommentDto productCommentDto);

    void deleteComment(Long productId, Long commentId);

    ProductCommentDto publishComment(Long productId, Long commentId);

    ProductCommentDto getComment(Long productId, Long commentId);

    Page<ProductCommentDto> getProductComments(Pageable pageable, Long productId);

    Page<ProductCommentDto> getAllComments(Pageable pageable);

    Page<ProductCommentDto> getAdminProductComments(Pageable pageable, AdminProductCommentFilter filter);

}
