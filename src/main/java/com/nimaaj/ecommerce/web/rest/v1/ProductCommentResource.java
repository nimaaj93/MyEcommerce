package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.ProductCommentDto;
import com.nimaaj.ecommerce.service.ProductCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product/{productId}/comment")
@RequiredArgsConstructor
@Validated
public class ProductCommentResource {

    private final ProductCommentService productCommentService;

    @PostMapping
    public ResponseEntity<ProductCommentDto> createComment(@PathVariable("productId") Long productId,
                                                           @Validated(ProductCommentDto.Create.class)
                                                           ProductCommentDto productCommentDto) {
        productCommentDto.setProductId(productId);
        return ResponseEntity.ok(productCommentService.addComment(productCommentDto));
    }

    @PutMapping
    public ResponseEntity<ProductCommentDto> updateComment(@PathVariable("productId") Long productId,
                                          @Validated(ProductCommentDto.Update.class)
                                          ProductCommentDto productCommentDto) {
        productCommentDto.setProductId(productId);
        return ResponseEntity.ok(productCommentService.updateComment(productCommentDto));
    }

    @PatchMapping("/api/v1/product/{productId}/comment/{commentId}/publish")
    public ResponseEntity<ProductCommentDto> publishComment(@PathVariable("productId") Long productId,
                                           @PathVariable("commentId") Long commentId) {
        return ResponseEntity.ok(productCommentService.publishComment(productId, commentId));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("productId") Long productId,
                                                    @PathVariable("commentId") Long commentId) {
        productCommentService.deleteComment(productId, commentId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ProductCommentDto>> getProductComments(@PathVariable("productId") Long productId,
                                                                      Pageable pageable) {
        return ResponseEntity.ok(productCommentService.getProductComments(pageable, productId));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<ProductCommentDto> getComment(@PathVariable("productId") Long productId,
                                                        @PathVariable("commentId") Long commentId) {
        return ResponseEntity.ok(productCommentService.getComment(productId, commentId));
    }


}
