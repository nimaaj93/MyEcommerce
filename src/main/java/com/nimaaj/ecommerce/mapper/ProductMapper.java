package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.Product;
import com.nimaaj.ecommerce.dto.FullProductDTO;
import com.nimaaj.ecommerce.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.function.Function;

/**
 * Created by K550 VX on 3/3/2019.
 */
@Mapper(componentModel = "spring", uses = { ProductDetailMapper.class, ProductMediaMapper.class })
public interface ProductMapper extends CommonMapper<Product, ProductDTO> {

    @Mappings({
            @Mapping(source = "productMediaRels", target = "productMediaList"),
    })
    FullProductDTO toFullProductDto(Product product);

}