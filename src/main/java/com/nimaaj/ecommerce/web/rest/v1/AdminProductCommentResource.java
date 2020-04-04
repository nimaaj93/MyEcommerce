package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.ProductCommentDto;
import com.nimaaj.ecommerce.model.input.AdminProductCommentFilter;
import com.nimaaj.ecommerce.service.ProductCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product-comment")
@RequiredArgsConstructor
public class AdminProductCommentResource {

    private final ProductCommentService productCommentService;

    @GetMapping
    public ResponseEntity<Page<ProductCommentDto>> getAllComments(Pageable pageable) {
        return ResponseEntity.ok(productCommentService.getAllComments(pageable));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Page<ProductCommentDto>> getProductComments(Pageable pageable,
                                                                      @Valid AdminProductCommentFilter filter) {
        return ResponseEntity.ok(productCommentService.getAdminProductComments(pageable, filter));
    }

}
