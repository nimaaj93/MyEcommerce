package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.UserBag;
import com.nimaaj.ecommerce.dto.UserBagDTO;
import com.nimaaj.ecommerce.exception.DuplicateProductUserBagException;
import com.nimaaj.ecommerce.exception.UserBagNotFoundException;
import com.nimaaj.ecommerce.mapper.UserBagMapper;
import com.nimaaj.ecommerce.repository.UserBagRepository;
import com.nimaaj.ecommerce.service.UserBagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * Created by K550 VX on 27.09.2019.
 */
@Service
@Transactional
@Validated
public class UserBagServiceImpl implements UserBagService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserBagServiceImpl.class);

    private final UserBagRepository userBagRepository;
    private final UserBagMapper userBagMapper;

    public UserBagServiceImpl(UserBagRepository userBagRepository, UserBagMapper userBagMapper) {
        this.userBagRepository = userBagRepository;
        this.userBagMapper = userBagMapper;
    }

    @Override
    public List<UserBagDTO> deleteItem(@NotNull Long id) {
        LOGGER.debug("deleteItem() called for id: {}", id);
        //TODO get user id from token
        userBagRepository.findById(id)
                .filter(userBag -> userBag.getUser().getId() == )
                .orElseThrow(UserBagNotFoundException::new);
        userBagRepository.deleteById(id);
        return getUserBag();
    }

    @Override
    public List<UserBagDTO> updateItem(UserBagDTO userBagDTO) {
        LOGGER.debug("updateItem() called for: {}", userBagDTO);
        //TODO get user id from token
        userBagRepository.findById(userBagDTO.getId())
                .filter(userBag -> userBag.getUser().getId() == )
                .orElseThrow(UserBagNotFoundException::new);
        final UserBag userBag = userBagMapper.toEntity(userBagDTO);
        userBagRepository.save(userBag);
        return getUserBag();
    }

    @Override
    public List<UserBagDTO> addItem(UserBagDTO userBagDTO) {
        LOGGER.debug("addItem() called for: {}", userBagDTO);
        Long productId = userBagDTO.getProductId();
        final Optional<UserBagDTO> existingUserBagForProduct = getUserBag().stream()
                .filter(currentItem -> currentItem.getProductId().equals(productId))
                .findAny();
        if (existingUserBagForProduct.isPresent()) {
            throw new DuplicateProductUserBagException();
        }
        userBagRepository.save(userBagMapper.toEntity(userBagDTO));
        return getUserBag();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserBagDTO> getUserBag() {
        //TODO get user id from token
        return userBagMapper.toDto(userBagRepository.findAllByUser_Id());
    }

}
