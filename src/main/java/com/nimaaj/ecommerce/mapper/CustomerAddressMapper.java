package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.CustomerAddress;
import com.nimaaj.ecommerce.dto.CustomerAddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CustomerAddressMapper extends CommonMapper<CustomerAddress, CustomerAddressDTO> {

    @Override
    @Mappings(
            @Mapping(source = "customer.id", target = "customerId")
    )
    CustomerAddressDTO toDto(CustomerAddress entity);

    @Mappings({
            @Mapping(target = "customer", ignore = true),
            @Mapping(target = "addressStatus", ignore = true),
            @Mapping(target = "defaultSelected", ignore = true),
    })
    void toEntity(@MappingTarget CustomerAddress entity, CustomerAddressDTO dto);

}