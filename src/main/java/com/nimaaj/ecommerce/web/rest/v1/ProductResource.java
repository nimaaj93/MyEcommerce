package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.ProductDto;
import com.nimaaj.ecommerce.model.input.AddProductModel;
import com.nimaaj.ecommerce.model.input.ProductFilterModel;
import com.nimaaj.ecommerce.model.input.UpdateProductModel;
import com.nimaaj.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDto>> getProducts(
            Pageable pageable, ProductFilterModel productFilterModel) {
        return ResponseEntity.ok(productService.searchProducts(pageable, productFilterModel));
    }

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@Valid @RequestBody AddProductModel model) {
        return ResponseEntity.ok(productService.addProduct(model));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<ProductDto> productByCode(@PathVariable("code") String code) {
        return ResponseEntity.ok(productService.getProductByCode(code));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id , @Valid @RequestBody UpdateProductModel model) {
        return ResponseEntity.ok(productService.updateProduct(id, model));
    }

}