package com.nimaaj.ecommerce.service.specification.factory.impl;

import com.nimaaj.ecommerce.domain.User;
import com.nimaaj.ecommerce.service.specification.factory.SpecificationFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Qualifier("userSearchSpecificationFactory")
public class UserSearchSpecificationFactory implements SpecificationFactory<User, String> {

    @Override
    public Specification<User> getSpecification(String filter) {
        if (!StringUtils.hasText(filter)) {
            return Specification.where(null);
        }
        return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.or(
                    criteriaBuilder.like(root.get("name"), "%" + filter + "%"),
                    criteriaBuilder.like(root.get("mobileNumber"), "%" + filter + "%"),
                    criteriaBuilder.like(root.get("nationalCode"), "%" + filter + "%"),
                    criteriaBuilder.like(root.get("email"), "%" + filter + "%")
            );
    }
}