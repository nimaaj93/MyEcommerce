package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.ProductQuantityDivisionDto;

import java.util.List;

public interface ProductQuantityDivisionService {

    List<ProductQuantityDivisionDto> getQuantityDivisionsForProduct(Long productId);

    ProductQuantityDivisionDto add(ProductQuantityDivisionDto productQuantityDivisionDto);

    ProductQuantityDivisionDto update(ProductQuantityDivisionDto productQuantityDivisionDto);

    void delete(Long productId, Long divisionId);

    ProductQuantityDivisionDto updateQuantityForDivision(Long productId, Long divisionId, Integer newQuantity);

    ProductQuantityDivisionDto increaseQuantityForDivision(Long productId, Long divisionId, Integer increment);

    ProductQuantityDivisionDto decreaseQuantityForDivision(Long productId, Long divisionId, Integer decrement);

}
