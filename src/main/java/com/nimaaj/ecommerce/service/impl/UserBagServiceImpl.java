package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.dto.UserBagDTO;
import com.nimaaj.ecommerce.repository.UserBagRepository;
import com.nimaaj.ecommerce.service.UserBagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Created by K550 VX on 27.09.2019.
 */
@Service
@Transactional
public class UserBagServiceImpl implements UserBagService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserBagServiceImpl.class);

    private final UserBagRepository userBagRepository;

    public UserBagServiceImpl(UserBagRepository userBagRepository) {
        this.userBagRepository = userBagRepository;
    }

    @Override
    public List<UserBagDTO> deleteItem(Long id) {
        LOGGER.debug("deleteItem() called for id: {}", id);
        return null;
    }

    @Override
    public List<UserBagDTO> updateItem(UserBagDTO userBagDTO) {
        return null;
    }

    @Override
    public List<UserBagDTO> addItem(UserBagDTO userBagDTO) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserBagDTO> getUserBag() {
        return null;
    }

}
