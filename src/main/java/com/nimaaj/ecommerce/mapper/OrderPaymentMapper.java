package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.OrderPayment;
import com.nimaaj.ecommerce.dto.OrderPaymentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = { DateMapper.class })
public interface OrderPaymentMapper extends CommonMapper<OrderPayment, OrderPaymentDto> {

    @Override
    @Mappings({
            @Mapping(source = "order.id", target = "orderId"),
            @Mapping(source = "user.id", target = "userId"),
    })
    OrderPaymentDto toDto(OrderPayment entity);
}
