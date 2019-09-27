package com.nimaaj.ecommerce.util.db;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * Created by K550 VX on 27.09.2019.
 */
public class SpecificationUtil {

    private SpecificationUtil() {
    }

    public static Predicate getFinalPredicate(
            CriteriaBuilder criteriaBuilder, List<Predicate> predicateList) {
        Predicate[] predicates = new Predicate[predicateList.size()];
        return criteriaBuilder.and(predicateList.toArray(predicates));
    }

}
