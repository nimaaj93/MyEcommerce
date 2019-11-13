package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.ProductCategoryAttr;
import com.nimaaj.ecommerce.dto.ProductCategoryAttrDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = { ProductCategoryMapper.class })
public interface ProductCategoryAttrMapper extends CommonMapper<ProductCategoryAttr, ProductCategoryAttrDTO> {

    @Override
    @Mappings({
            @Mapping(source = "productCategoryId", target = "productCategory")
    })
    ProductCategoryAttr toEntity(ProductCategoryAttrDTO dto);

    @Override
    @Mappings({
            @Mapping(source = "productCategory.id", target = "productCategoryId")
    })
    ProductCategoryAttrDTO toDto(ProductCategoryAttr entity);
}