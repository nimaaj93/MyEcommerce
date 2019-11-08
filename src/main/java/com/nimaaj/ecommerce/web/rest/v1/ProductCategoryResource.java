package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.ProductCategoryDTO;
import com.nimaaj.ecommerce.dto.ProductCategoryTreeDTO;
import com.nimaaj.ecommerce.service.ProductCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-category")
public class ProductCategoryResource {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryResource(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<ProductCategoryDTO>> getAllProductCategories() {
        return ResponseEntity.ok(productCategoryService.getAllProductCategories());
    }

    @GetMapping("/tree")
    public ResponseEntity<List<ProductCategoryTreeDTO>> getAllProductCategoriesTree() {
        return ResponseEntity.ok(productCategoryService.getAllProductCategoriesTree());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productCategoryService.getById(id));
    }

    @PatchMapping("/{id}/order/{orderVal}")
    public ResponseEntity<ProductCategoryDTO> reorder(@PathVariable("id") Long id,
                                                      @PathVariable("orderVal") Integer orderVal) {
        return ResponseEntity.ok(productCategoryService.reorder(id, orderVal));
    }

}