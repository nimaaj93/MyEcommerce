package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.UserBagDto;

import java.util.List;

/**
 * Created by K550 VX on 27.09.2019.
 */
public interface UserBagService {

    List<UserBagDto> deleteItem(Long id);

    List<UserBagDto> updateItem(UserBagDto userBagDTO);

    List<UserBagDto> addItem(UserBagDto userBagDTO);

    List<UserBagDto> getUserBag();
}