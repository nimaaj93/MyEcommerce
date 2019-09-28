package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.UserBagDTO;

import java.util.List;

/**
 * Created by K550 VX on 27.09.2019.
 */
public interface UserBagService {

    List<UserBagDTO> deleteItem(Long id);

    List<UserBagDTO> updateItem(UserBagDTO userBagDTO);

    List<UserBagDTO> addItem(UserBagDTO userBagDTO);

    List<UserBagDTO> getUserBag();
}