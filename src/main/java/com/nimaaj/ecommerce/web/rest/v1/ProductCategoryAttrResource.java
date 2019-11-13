package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.ProductCategoryAttrDTO;
import com.nimaaj.ecommerce.service.ProductCategoryAttrService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-category/{categoryId}/attr")
@Validated
public class ProductCategoryAttrResource {

    private final ProductCategoryAttrService productCategoryAttrService;

    public ProductCategoryAttrResource(ProductCategoryAttrService productCategoryAttrService) {
        this.productCategoryAttrService = productCategoryAttrService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryAttrDTO> getById(
            @PathVariable("categoryId") Long categoryId,
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(productCategoryAttrService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductCategoryAttrDTO>> getAllByCategoryId(
            @PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(productCategoryAttrService.getAllByProductCategoryId(categoryId));
    }

    @PostMapping
    public ResponseEntity<ProductCategoryAttrDTO> create(
            @PathVariable("categoryId") Long categoryId,
            @Validated(ProductCategoryAttrDTO.Create.class)
            @RequestBody ProductCategoryAttrDTO productCategoryAttrDTO) {
        return ResponseEntity.ok(productCategoryAttrService.create(productCategoryAttrDTO));
    }

    @PutMapping
    public ResponseEntity<ProductCategoryAttrDTO> update(
            @PathVariable("categoryId") Long categoryId,
            @Validated(ProductCategoryAttrDTO.Update.class)
            @RequestBody ProductCategoryAttrDTO productCategoryAttrDTO) {
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