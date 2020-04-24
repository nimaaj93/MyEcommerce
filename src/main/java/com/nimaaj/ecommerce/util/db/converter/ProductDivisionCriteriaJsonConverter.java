package com.nimaaj.ecommerce.util.db.converter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimaaj.ecommerce.domain.ProductQuantityDivisionCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;

@Converter
@Component
@Slf4j
public class ProductDivisionCriteriaJsonConverter
        implements AttributeConverter<ArrayList<ProductQuantityDivisionCriteria>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ProductDivisionCriteriaJsonConverter() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public String convertToDatabaseColumn(ArrayList<ProductQuantityDivisionCriteria> productQuantityDivisionCriteria) {
        if (CollectionUtils.isEmpty(productQuantityDivisionCriteria)) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(productQuantityDivisionCriteria);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<ProductQuantityDivisionCriteria> convertToEntityAttribute(String s) {
        if (!StringUtils.hasText(s)) {
            return new ArrayList<>();
        }

        JavaType type = objectMapper.getTypeFactory().
                constructCollectionType(ArrayList.class, ProductQuantityDivisionCriteria.class);
        try {
            return objectMapper.readValue(s, type);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
