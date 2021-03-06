package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.Customer;
import com.nimaaj.ecommerce.dto.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends CommonMapper<Customer, CustomerDto> {

}