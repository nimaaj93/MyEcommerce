package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.ProductCategory;
import com.nimaaj.ecommerce.exception.ProductCategoryNotFoundException;
import com.nimaaj.ecommerce.repository.ProductCategoryRepository;
import com.nimaaj.ecommerce.service.ProductCategoryService;
import com.nimaaj.ecommerce.util.Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by K550 VX on 3/5/2019.
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<Long> getInclusiveChildrenCategoryIdsForProductCategory(Long productCategoryId) {

        ProductCategory productCategory = productCategoryRepository.findById(productCategoryId)
                .orElseThrow(ProductCategoryNotFoundException::new);

        List<Long> categoryIds = loadNextChildrenLevel(productCategory)
                .stream()
                .map(ProductCategory::getId)
                .collect(Collectors.toList());
        categoryIds.add(productCategory.getId());

        return categoryIds;
    }

    private List<ProductCategory> loadNextChildrenLevel(ProductCategory productCategory) {

        List<ProductCategory> result = new ArrayList<>();
        List<ProductCategory> children =
                productCategoryRepository.findAllByParent_Id(productCategory.getId());

        if (Empty.isNotEmpty(children)) {
            result.addAll(children);
            children.forEach(child -> {
                List<ProductCategory> grandChildren = loadNextChildrenLevel(child);
                if (Empty.isNotEmpty(grandChildren)) {
                    result.addAll(grandChildren);
                }
            });
        }

        return result;
    }

}