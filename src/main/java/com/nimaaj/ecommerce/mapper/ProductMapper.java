package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.Product;
import com.nimaaj.ecommerce.dto.ProductDto;
import com.nimaaj.ecommerce.model.input.AddProductModel;
import com.nimaaj.ecommerce.model.input.UpdateProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

/**
 * Created by K550 VX on 3/3/2019.
 */
@Mapper(componentModel = "spring",
        uses = { ProductDetailMapper.class,
                ProductMediaMapper.class,
                ProductCategoryMapper.class,
                ManufacturerMapper.class })
public interface ProductMapper extends CommonMapper<Product, ProductDto> {

    @Override
    @Mappings({
            @Mapping(source = "productMediaRels", target = "productMediaList"),
    })
    ProductDto toDto(Product entity);

    @Mappings({
            @Mapping(source = "manufacturerId", target = "manufacturer"),
            @Mapping(source = "categoryId", target = "category")
    })
    Product toEntity(AddProductModel addProductModel);

    @Mappings({
            @Mapping(source = "manufacturerId", target = "manufacturer"),
            @Mapping(source = "categoryId", target = "category")
    })
    void mapUpdateModel(UpdateProductModel model,@MappingTarget Product product);

}