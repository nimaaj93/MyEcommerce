package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.UserBag;
import com.nimaaj.ecommerce.dto.UserBagDTO;
import org.mapstruct.Mapper;

/**
 * Created by K550 VX on 18.10.2019.
 */
@Mapper(componentModel = "spring", uses = DateMapper.class)
public interface UserBagMapper extends CommonMapper<UserBag, UserBagDTO> {



}