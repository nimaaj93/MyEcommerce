package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.ProductQa;
import com.nimaaj.ecommerce.domain.User;
import com.nimaaj.ecommerce.dto.ProductNewQuestionDto;
import com.nimaaj.ecommerce.dto.ProductQaDto;
import com.nimaaj.ecommerce.security.SecurityUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { DateMapper.class, UserMapper.class })
public interface ProductQaMapper extends CommonMapper<ProductQa, ProductQaDto> {

    @Override
    @Mapping(source = "product.id", target = "productId")
    ProductQaDto toDto(ProductQa entity);

    default ProductQa toEntity(ProductNewQuestionDto productNewQuestionDto) {
        ProductQa productQa = new ProductQa();
        productQa.setQuestion(productNewQuestionDto.getQuestion());
        productQa.setAskUser(getCurrentUser());
        return productQa;
    }

    private User getCurrentUser() {
        return SecurityUtils.getCurrentUserId()
                .map(User::new)
                .orElseThrow(IllegalStateException::new);
    }

}
