package com.nimaaj.ecommerce.service.specification.factory;

import org.springframework.data.jpa.domain.Specification;

/**
 * Created by K550 VX on 27.09.2019.
 */
public interface SpecificationFactory<T,V> {

    Specification<T> getSpecification(V filter);

}