package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.Product;
import com.nimaaj.ecommerce.dto.ProductDTO;
import org.mapstruct.Mapper;

/**
 * Created by K550 VX on 3/3/2019.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper extends CommonMapper<Product, ProductDTO> {
}
