package com.nimaaj.ecommerce.service.specification.factory.impl;

import com.nimaaj.ecommerce.domain.Product;
import com.nimaaj.ecommerce.domain.ProductComment;
import com.nimaaj.ecommerce.model.input.AdminProductCommentFilter;
import com.nimaaj.ecommerce.service.specification.factory.SpecificationFactory;
import com.nimaaj.ecommerce.util.db.SpecificationUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component("productCommentSearchSpecificationFactory")
public class ProductCommentSearchSpecificationFactory
        implements SpecificationFactory<ProductComment, AdminProductCommentFilter> {

    @Override
    public Specification<ProductComment> getSpecification(AdminProductCommentFilter filter) {


        return (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicateList = new ArrayList<>();

            Predicate productIdPredicate = criteriaQuery.from(Product.class).get("id").in(List.of(filter.getProductId()));
            predicateList.add(productIdPredicate);

            if (StringUtils.hasText(filter.getQ())) {
                Predicate commentSearchPredicate = criteriaBuilder.like(root.get("comment"), "%" + filter.getQ() + "%");
                predicateList.add(commentSearchPredicate);
            }

            return SpecificationUtil.getFinalPredicate(criteriaBuilder, predicateList);
        };
    }
}
