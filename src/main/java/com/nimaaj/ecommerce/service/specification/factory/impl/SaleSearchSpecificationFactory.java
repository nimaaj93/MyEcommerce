package com.nimaaj.ecommerce.service.specification.factory.impl;

import com.nimaaj.ecommerce.domain.Sale;
import com.nimaaj.ecommerce.enumaration.SaleStatus;
import com.nimaaj.ecommerce.model.input.SaleFilter;
import com.nimaaj.ecommerce.service.specification.factory.SpecificationFactory;
import com.nimaaj.ecommerce.util.db.SpecificationUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("saleSearchSpecificationFactory")
public class SaleSearchSpecificationFactory implements SpecificationFactory<Sale, SaleFilter> {

    @Override
    public Specification<Sale> getSpecification(SaleFilter filter) {

        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (filter.getStatus() != null) {
                Predicate saleStatusPredicate =
                        criteriaBuilder.equal(root.get("saleStatus"), filter.getStatus());
                predicateList.add(saleStatusPredicate);
            }

            if (StringUtils.hasText(filter.getQuery())) {
                Predicate queryPredicate =
                        criteriaBuilder.like(root.get("description"), "%" + filter.getQuery() + "%");
                predicateList.add(queryPredicate);
            }

            if (filter.getType() != null) {
                Predicate saleTypePredicate =
                        criteriaBuilder.equal(root.get("saleType"), filter.getType());
                predicateList.add(saleTypePredicate);
            }

            return SpecificationUtil.getFinalPredicate(criteriaBuilder, predicateList);
        };
    }
}
