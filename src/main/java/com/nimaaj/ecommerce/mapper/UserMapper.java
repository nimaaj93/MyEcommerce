package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.User;
import com.nimaaj.ecommerce.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { DateMapper.class, CustomerMapper.class })
public interface UserMapper extends CommonMapper<User, UserDTO> {

}