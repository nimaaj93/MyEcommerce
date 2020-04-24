package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.ProductDetail;
import com.nimaaj.ecommerce.dto.ProductDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Created by K550 VX on 27.09.2019.
 */
@Mapper(componentModel = "spring")
public interface ProductDetailMapper extends CommonMapper<ProductDetail, ProductDetailDto> {

    @Override
    @Mappings({
            @Mapping(source = "product.id", target = "productId")
    })
    ProductDetailDto toDto(ProductDetail entity);
}
