package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.ShippingResource;
import com.nimaaj.ecommerce.dto.ShippingResourceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { DateMapper.class })
public interface ShippingResourceMapper extends CommonMapper<ShippingResource, ShippingResourceDTO> {
}