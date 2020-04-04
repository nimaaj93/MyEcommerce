package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.ProductComment;
import com.nimaaj.ecommerce.dto.ProductCommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = { DateMapper.class })
public interface ProductCommentMapper extends CommonMapper<ProductComment, ProductCommentDto> {

    @Override
    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "product.id", target = "productId"),
            @Mapping(source = "parent.id", target = "parentId"),
    })
    ProductCommentDto toDto(ProductComment entity);

}
