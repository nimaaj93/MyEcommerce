package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.Application;
import com.nimaaj.ecommerce.dto.ApplicationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationMapper extends CommonMapper<Application, ApplicationDto> {
}