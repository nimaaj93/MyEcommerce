package com.nimaaj.ecommerce.enumaration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.nimaaj.ecommerce.enumaration.behaviour.SaleRefValidate;
import com.nimaaj.ecommerce.exception.InvalidInputDataException;
import com.nimaaj.ecommerce.exception.InvalidJsonException;
import com.nimaaj.ecommerce.exception.ProductNotFoundException;
import com.nimaaj.ecommerce.service.ProductCategoryService;
import com.nimaaj.ecommerce.service.ProductService;
import com.nimaaj.ecommerce.util.context.StaticContextAccessor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashSet;

@Slf4j
public enum SaleType implements SaleRefValidate {

    GENERAL(false) {
        @Override
        public void validateRef(String ref) {
        }
    },
    CATEGORY(true) {
        @Override
        public void validateRef(String ref) {
            StaticContextAccessor.getBean(ProductCategoryService.class)
                    .getById(Long.parseLong(ref));
        }
    },
    PRODUCT(true) {
        @Override
        public void validateRef(String ref) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                CollectionType collectionType =
                        objectMapper.getTypeFactory().constructCollectionType(HashSet.class, Long.class);
                HashSet<Long> productIds = objectMapper.readValue(ref, collectionType);
                if (productIds.isEmpty()) {
                    throw new InvalidInputDataException();
                }
                if (StaticContextAccessor.getBean(ProductService.class)
                        .getProductsByIds(productIds)
                        .size() != productIds.size()) {
                    throw new ProductNotFoundException();
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                throw new InvalidJsonException(e.getMessage(), e);
            }
        }
    },
    ;

    private final boolean acceptRef;

    SaleType(boolean acceptRef) {
        this.acceptRef = acceptRef;
    }

    public boolean isAcceptRef() {
        return acceptRef;
    }

}
