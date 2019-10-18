package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.ProductMediaRel;
import com.nimaaj.ecommerce.dto.ProductMediaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Created by K550 VX on 27.09.2019.
 */
@Mapper(componentModel = "spring", uses = MediaMapper.class)
public interface ProductMediaMapper extends CommonMapper<ProductMediaRel, ProductMediaDTO> {

    @Override
    @Mappings({
            @Mapping(source = "product.id", target = "productId")
    })
    ProductMediaDTO toDto(ProductMediaRel entity);
}