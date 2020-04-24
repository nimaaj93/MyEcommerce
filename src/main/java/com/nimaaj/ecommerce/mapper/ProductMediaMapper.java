package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.ProductMediaRel;
import com.nimaaj.ecommerce.dto.ProductMediaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Created by K550 VX on 27.09.2019.
 */
@Mapper(componentModel = "spring", uses = MediaMapper.class)
public interface ProductMediaMapper extends CommonMapper<ProductMediaRel, ProductMediaDto> {

    @Override
    @Mappings({
            @Mapping(source = "product.id", target = "productId")
    })
    ProductMediaDto toDto(ProductMediaRel entity);
}