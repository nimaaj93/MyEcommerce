package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.ProductCategoryAttrDTO;

import java.util.List;

public interface ProductCategoryAttrService {

    ProductCategoryAttrDTO create(ProductCategoryAttrDTO productCategoryAttrDTO);

    ProductCategoryAttrDTO update(ProductCategoryAttrDTO productCategoryAttrDTO);

    List<ProductCategoryAttrDTO> getAllByProductCategoryId(Long productCategoryId);

    ProductCategoryAttrDTO getById(Long id);

    void delete(Long id);

}