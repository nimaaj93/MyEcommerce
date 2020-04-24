package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.DiscountCode;
import com.nimaaj.ecommerce.dto.DiscountCodeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = { DateMapper.class })
public interface DiscountCodeMapper extends CommonMapper<DiscountCode, DiscountCodeDto> {

    @Mappings({
            @Mapping(target = "state", ignore = true),
            @Mapping(target = "codeVal", ignore = true),
    })
    void toEntity(@MappingTarget DiscountCode discountCode, DiscountCodeDto discountCodeDTO);

}