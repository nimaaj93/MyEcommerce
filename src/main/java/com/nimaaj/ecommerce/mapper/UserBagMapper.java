package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.UserBag;
import com.nimaaj.ecommerce.dto.UserBagDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserBagMapper extends CommonMapper<UserBag, UserBagDTO> {
}