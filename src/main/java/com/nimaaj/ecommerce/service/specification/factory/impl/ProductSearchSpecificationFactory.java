package com.nimaaj.ecommerce.service.specification.factory.impl;

import com.nimaaj.ecommerce.domain.Product;
import com.nimaaj.ecommerce.domain.ProductCategory;
import com.nimaaj.ecommerce.model.input.ProductFilterModel;
import com.nimaaj.ecommerce.service.ProductCategoryService;
import com.nimaaj.ecommerce.service.specification.factory.SpecificationFactory;
import com.nimaaj.ecommerce.util.Empty;
import com.nimaaj.ecommerce.util.db.SpecificationUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by K550 VX on 27.09.2019.
 */
@Component
@Qualifier("productSearchSpecificationFactory")
public class ProductSearchSpecificationFactory
        implements SpecificationFactory<Product, ProductFilterModel> {

    private final ProductCategoryService productCategoryService;

    public ProductSearchSpecificationFactory(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @Override
    public Specification<Product> getSpecification(ProductFilterModel filter) {

        Specification<Product> specification = (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicateList = new ArrayList<>();

            if (Empty.isNotEmpty(filter.getCategoryId())) {
                final List<Long> categoryIds = productCategoryService
                        .getInclusiveChildrenCategoryIdsForProductCategory(filter.getCategoryId());
                Expression<ProductCategory> productCategoryExpression =
                        criteriaQuery.from(ProductCategory.class).get("id");
                Predicate categoryInExpression = productCategoryExpression.in(categoryIds);
                predicateList.add(categoryInExpression);
            }

            if (Empty.isNotEmpty(filter.getPriceFrom())) {
                Predicate priceFromPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("price"), filter.getPriceFrom());
                predicateList.add(priceFromPredicate);
            }

            if (Empty.isNotEmpty(filter.getPriceTo())) {
                Predicate priceToPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("price"), filter.getPriceTo());
                predicateList.add(priceToPredicate);
            }

            if (!Empty.isBlank(filter.getSearchQuery())) {
                Predicate textSearchCriteria = criteriaBuilder.or(
                    criteriaBuilder.like(root.get("titleEn"), "%" + filter.getSearchQuery() + "%"),
                    criteriaBuilder.like(root.get("titleFa"), "%" + filter.getSearchQuery() + "%")
                );
                predicateList.add(textSearchCriteria);
            }

            return SpecificationUtil.getFinalPredicate(criteriaBuilder, predicateList);
        };

        return specification;
    }
}
