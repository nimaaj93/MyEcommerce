package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.Sale;
import com.nimaaj.ecommerce.dto.SaleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { DateMapper.class })
public interface SaleMapper extends CommonMapper<Sale, SaleDto> {
}
