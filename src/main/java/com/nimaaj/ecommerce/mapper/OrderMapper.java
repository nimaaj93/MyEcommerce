package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.Order;
import com.nimaaj.ecommerce.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = { DateMapper.class,
        OrderItemMapper.class,
        OrderPaymentMapper.class,
        OrderShippingMapper.class,
        InvoiceMapper.class})
public interface OrderMapper extends CommonMapper<Order, OrderDto> {

    @Override
    @Mappings({
            @Mapping(source = "user.id", target = "userId")
    })
    OrderDto toDto(Order entity);
}
