package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.domain.ProductCategory;

import java.util.List;

/**
 * Created by K550 VX on 3/5/2019.
 */
public interface ProductCategoryService {

    List<Long> getInclusiveChildrenCategoryIdsForProductCategory(Long productCategoryId);

}
