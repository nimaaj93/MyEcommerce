package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.ProductCategoryAttrDto;
import com.nimaaj.ecommerce.service.ProductCategoryAttrService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-category/{categoryId}/attr")
@Validated
@RequiredArgsConstructor
public class ProductCategoryAttrResource {

    private final ProductCategoryAttrService productCategoryAttrService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryAttrDto> getById(
            @PathVariable("categoryId") Long categoryId,
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(productCategoryAttrService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductCategoryAttrDto>> getAllByCategoryId(
            @PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(productCategoryAttrService.getAllByProductCategoryId(categoryId));
    }

    @PostMapping
    public ResponseEntity<ProductCategoryAttrDto> create(
            @PathVariable("categoryId") Long categoryId,
            @Validated(ProductCategoryAttrDto.Create.class)
            @RequestBody ProductCategoryAttrDto productCategoryAttrDTO) {
        return ResponseEntity.ok(productCategoryAttrService.create(productCategoryAttrDTO));
    }

    @PutMapping
    public ResponseEntity<ProductCategoryAttrDto> update(
            @PathVariable("categoryId") Long categoryId,
            @Validated(ProductCategoryAttrDto.Update.class)
            @RequestBody ProductCategoryAttrDto productCategoryAttrDTO) {
        return ResponseEntity.ok(productCategoryAttrService.update(productCategoryAttrDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(
            @PathVariable("categoryId") Long categoryId,
            @PathVariable("id") Long id) {
        productCategoryAttrService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}