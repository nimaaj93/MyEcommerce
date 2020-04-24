package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.ProductCategoryDto;
import com.nimaaj.ecommerce.dto.ProductCategoryTreeDto;
import com.nimaaj.ecommerce.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-category")
@RequiredArgsConstructor
public class ProductCategoryResource {

    private final ProductCategoryService productCategoryService;

    @GetMapping
    public ResponseEntity<List<ProductCategoryDto>> getAllProductCategories() {
        return ResponseEntity.ok(productCategoryService.getAllProductCategories());
    }

    @GetMapping("/tree")
    public ResponseEntity<List<ProductCategoryTreeDto>> getAllProductCategoriesTree() {
        return ResponseEntity.ok(productCategoryService.getAllProductCategoriesTree());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productCategoryService.getById(id));
    }

    @PatchMapping("/{id}/order/{orderVal}")
    public ResponseEntity<ProductCategoryDto> reorder(@PathVariable("id") Long id,
                                                      @PathVariable("orderVal") Integer orderVal) {
        return ResponseEntity.ok(productCategoryService.reorder(id, orderVal));
    }

}