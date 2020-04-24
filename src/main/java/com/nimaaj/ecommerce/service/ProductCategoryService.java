package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.ProductCategoryDto;
import com.nimaaj.ecommerce.dto.ProductCategoryTreeDto;
import com.nimaaj.ecommerce.model.input.AddProductCategoryModel;
import com.nimaaj.ecommerce.model.input.UpdateProductCategoryModel;

import java.util.List;

/**
 * Created by K550 VX on 3/5/2019.
 */
public interface ProductCategoryService {

    List<Long> getInclusiveChildrenCategoryIdsForProductCategory(Long productCategoryId);

    List<ProductCategoryDto> getAllProductCategories();

    List<ProductCategoryTreeDto> getAllProductCategoriesTree();

    ProductCategoryDto create(AddProductCategoryModel model);

    ProductCategoryDto update(UpdateProductCategoryModel model);

    ProductCategoryDto getById(Long id);

    ProductCategoryDto reorder(Long id, Integer orderVal);

}
