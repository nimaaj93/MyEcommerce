package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.OrderItem;
import com.nimaaj.ecommerce.dto.OrderItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ProductMapper.class, SaleMapper.class })
public interface OrderItemMapper extends CommonMapper<OrderItem, OrderItemDto> {
}
