package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.ProductTagRel;
import com.nimaaj.ecommerce.dto.ProductTagRelDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ProductTagMapper.class, ProductMapper.class })
public interface ProductTagRelMapper extends CommonMapper<ProductTagRel, ProductTagRelDto> {

}
