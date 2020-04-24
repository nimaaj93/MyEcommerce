package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.ProductCategoryAttrDto;

import java.util.List;

public interface ProductCategoryAttrService {

    ProductCategoryAttrDto create(ProductCategoryAttrDto productCategoryAttrDTO);

    ProductCategoryAttrDto update(ProductCategoryAttrDto productCategoryAttrDTO);

    List<ProductCategoryAttrDto> getAllByProductCategoryId(Long productCategoryId);

    ProductCategoryAttrDto getById(Long id);

    void delete(Long id);

}