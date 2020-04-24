package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.ProductQuantityDivisionCriteria;
import com.nimaaj.ecommerce.dto.ProductQuantityDivisionCriteriaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductQuantityDivisionCriteriaMapper
        extends CommonMapper<ProductQuantityDivisionCriteria, ProductQuantityDivisionCriteriaDto> {

}
