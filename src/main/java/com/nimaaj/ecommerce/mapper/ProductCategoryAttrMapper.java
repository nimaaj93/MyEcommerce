package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.ProductCategoryAttr;
import com.nimaaj.ecommerce.dto.ProductCategoryAttrDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = { ProductCategoryMapper.class })
public interface ProductCategoryAttrMapper extends CommonMapper<ProductCategoryAttr, ProductCategoryAttrDto> {

    @Override
    @Mappings({
            @Mapping(source = "productCategoryId", target = "productCategory")
    })
    ProductCategoryAttr toEntity(ProductCategoryAttrDto dto);

    @Override
    @Mappings({
            @Mapping(source = "productCategory.id", target = "productCategoryId")
    })
    ProductCategoryAttrDto toDto(ProductCategoryAttr entity);
}