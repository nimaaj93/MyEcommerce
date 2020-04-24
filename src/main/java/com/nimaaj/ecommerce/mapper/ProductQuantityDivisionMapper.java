package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.ProductQuantityDivision;
import com.nimaaj.ecommerce.dto.ProductQuantityDivisionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = { ProductQuantityDivisionCriteriaMapper.class })
public interface ProductQuantityDivisionMapper
        extends CommonMapper<ProductQuantityDivision, ProductQuantityDivisionDto> {

    @Override
    @Mapping(source = "product.id", target = "productId")
    ProductQuantityDivisionDto toDto(ProductQuantityDivision entity);

    @Mapping(target = "product", ignore = true)
    void toEntityForUpdate(@MappingTarget ProductQuantityDivision entity, ProductQuantityDivisionDto dto);

}
