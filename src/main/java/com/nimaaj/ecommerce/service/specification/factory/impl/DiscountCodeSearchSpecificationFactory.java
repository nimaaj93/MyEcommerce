package com.nimaaj.ecommerce.service.specification.factory.impl;

import com.nimaaj.ecommerce.domain.DiscountCode;
import com.nimaaj.ecommerce.service.specification.factory.SpecificationFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Qualifier("discountCodeSearchSpecificationFactory")
public class DiscountCodeSearchSpecificationFactory
        implements SpecificationFactory<DiscountCode, String> {

    @Override
    public Specification<DiscountCode> getSpecification(String filter) {
        if (!StringUtils.hasText(filter)) {
            return Specification.where(null);
        }

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.or(
                        criteriaBuilder.like(root.get("title"), "%" + filter + "%"),
                        criteriaBuilder.like(root.get("codeVal"), "%" + filter + "%"),
                        criteriaBuilder.like(root.get("description"), "%" + filter + "%")
                );
    }
}