package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.ProductCategory;
import com.nimaaj.ecommerce.dto.ProductCategoryDto;
import com.nimaaj.ecommerce.dto.ProductCategoryTreeDto;
import com.nimaaj.ecommerce.model.input.AddProductCategoryModel;
import com.nimaaj.ecommerce.model.input.UpdateProductCategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

/**
 * Created by K550 VX on 27.10.2019.
 */
@Mapper(componentModel = "spring")
public interface ProductCategoryMapper extends CommonMapper<ProductCategory, ProductCategoryDto> {

    default ProductCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(id);
        return productCategory;
    }

    ProductCategoryTreeDto toTreeDto(ProductCategory productCategory);

    @Mappings({
            @Mapping(source = "parentId", target = "parent")
    })
    ProductCategory toEntity(AddProductCategoryModel model);

    @Mappings({
            @Mapping(source = "parentId", target = "parent")
    })
    void mapForUpdate(@MappingTarget ProductCategory productCategory,
                      UpdateProductCategoryModel model);

}