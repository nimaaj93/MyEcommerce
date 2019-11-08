package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.domain.ProductCategory;
import com.nimaaj.ecommerce.dto.ProductCategoryDTO;
import com.nimaaj.ecommerce.dto.ProductCategoryTreeDTO;
import com.nimaaj.ecommerce.model.input.AddProductCategoryModel;
import com.nimaaj.ecommerce.model.input.UpdateProductCategoryModel;

import java.util.List;

/**
 * Created by K550 VX on 3/5/2019.
 */
public interface ProductCategoryService {

    List<Long> getInclusiveChildrenCategoryIdsForProductCategory(Long productCategoryId);

    List<ProductCategoryDTO> getAllProductCategories();

    List<ProductCategoryTreeDTO> getAllProductCategoriesTree();

    ProductCategoryDTO create(AddProductCategoryModel model);

    ProductCategoryDTO update(UpdateProductCategoryModel model);

    ProductCategoryDTO getById(Long id);

    ProductCategoryDTO reorder(Long id, Integer orderVal);

}
