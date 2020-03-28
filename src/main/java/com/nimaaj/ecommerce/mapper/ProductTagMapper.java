package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.ProductTag;
import com.nimaaj.ecommerce.dto.ProductTagDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductTagMapper extends CommonMapper<ProductTag, ProductTagDto> {
}
