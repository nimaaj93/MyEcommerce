package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.ProductCategory;
import com.nimaaj.ecommerce.dto.ProductCategoryDTO;
import org.mapstruct.Mapper;

/**
 * Created by K550 VX on 27.10.2019.
 */
@Mapper(componentModel = "spring")
public interface ProductCategoryMapper extends CommonMapper<ProductCategory, ProductCategoryDTO> {

    default ProductCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(id);
        return productCategory;
    }

}