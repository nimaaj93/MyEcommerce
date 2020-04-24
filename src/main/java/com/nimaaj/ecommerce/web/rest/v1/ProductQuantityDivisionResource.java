package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.ProductQuantityDivisionDto;
import com.nimaaj.ecommerce.service.ProductQuantityDivisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product/{productId}/quantity-division")
@Validated
@RequiredArgsConstructor
public class ProductQuantityDivisionResource {

    private final ProductQuantityDivisionService productQuantityDivisionService;

    @GetMapping
    public ResponseEntity<List<ProductQuantityDivisionDto>> getProductQuantityDivisions(
            @PathVariable("productId") Long productId) {
        return ResponseEntity.ok(productQuantityDivisionService.getQuantityDivisionsForProduct(productId));
    }

    @PostMapping
    public ResponseEntity<ProductQuantityDivisionDto> create(@PathVariable("productId") Long productId,
                                                             @Validated(ProductQuantityDivisionDto.Create.class)
                                                             @RequestBody ProductQuantityDivisionDto productQuantityDivisionDto) {
        productQuantityDivisionDto.setProductId(productId);
        return ResponseEntity.ok(productQuantityDivisionService.add(productQuantityDivisionDto));
    }

    @PutMapping
    public ResponseEntity<ProductQuantityDivisionDto> update(@PathVariable("productId") Long productId,
                                                             @Validated(ProductQuantityDivisionDto.Update.class)
                                                             @RequestBody ProductQuantityDivisionDto productQuantityDivisionDto) {
        productQuantityDivisionDto.setProductId(productId);
        return ResponseEntity.ok(productQuantityDivisionService.update(productQuantityDivisionDto));
    }

    @DeleteMapping("/{divisionId}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("productId") Long productId,
                                             @PathVariable("divisionId") Long divisionId) {
        productQuantityDivisionService.delete(productId, divisionId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{divisionId}/quantity/{newQuantity}")
    public ResponseEntity<ProductQuantityDivisionDto> updateQuantity(@PathVariable("productId") Long productId,
                                                                     @PathVariable("divisionId") Long divisionId,
                                                                     @PathVariable("newQuantity") Integer newQuantity) {
        return ResponseEntity.ok(
                productQuantityDivisionService.updateQuantityForDivision(productId, divisionId, newQuantity));
    }

}
