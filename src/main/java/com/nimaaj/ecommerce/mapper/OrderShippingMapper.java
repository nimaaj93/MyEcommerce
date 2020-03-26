package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.OrderShipping;
import com.nimaaj.ecommerce.dto.OrderShippingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = { DateMapper.class })
public interface OrderShippingMapper extends CommonMapper<OrderShipping, OrderShippingDto> {

    @Override
    @Mappings({
            @Mapping(source = "resource.id", target = "resourceId"),
            @Mapping(source = "order.id", target = "orderId")
    })
    OrderShippingDto toDto(OrderShipping entity);
}
