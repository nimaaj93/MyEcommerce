package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.dto.UserBagDTO;
import com.nimaaj.ecommerce.exception.DuplicateUserBagException;
import com.nimaaj.ecommerce.exception.UserBagNotFoundException;
import com.nimaaj.ecommerce.mapper.UserBagMapper;
import com.nimaaj.ecommerce.repository.UserBagRepository;
import com.nimaaj.ecommerce.security.SecurityUtils;
import com.nimaaj.ecommerce.service.UserBagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by K550 VX on 27.09.2019.
 */
@Service
@Transactional
public class UserBagServiceImpl implements UserBagService {

    private final static Logger log = LoggerFactory.getLogger(UserBagServiceImpl.class);

    private final UserBagRepository userBagRepository;
    private final UserBagMapper userBagMapper;

    public UserBagServiceImpl(UserBagRepository userBagRepository, UserBagMapper userBagMapper) {
        this.userBagRepository = userBagRepository;
        this.userBagMapper = userBagMapper;
    }

    @Override
    public List<UserBagDTO> deleteItem(Long id) {
        log.debug("deleteItem() called for id: {}", id);
        Long userId = SecurityUtils.getCurrentUserId()
                .orElseThrow(() -> new IllegalStateException("No logged in user found in security context!"));

        userBagRepository.findById(id)
                .filter(userBag -> userBag.getUser().getId().equals(userId))
                .orElseThrow(UserBagNotFoundException::new);
        userBagRepository.deleteById(id);

        return getUserBag();
    }

    @Override
    public List<UserBagDTO> updateItem(UserBagDTO userBagDTO) {
        log.debug("updateItem() called for {}", userBagDTO);
        Long userId = SecurityUtils.getCurrentUserId()
                .orElseThrow(() -> new IllegalStateException("No logged in user found in security context!"));

        userBagRepository.findById(userBagDTO.getId())
                .filter(userBag -> userBag.getUser().getId().equals(userId))
                .orElseThrow(UserBagNotFoundException::new);
        userBagRepository.save(userBagMapper.toEntity(userBagDTO));

        return getUserBag();
    }

    @Override
    public List<UserBagDTO> addItem(UserBagDTO userBagDTO) {
        log.debug("addItem() called for {}", userBagDTO);

        Long userId = SecurityUtils.getCurrentUserId()
                .orElseThrow(() -> new IllegalStateException("No logged in user found in security context!"));

        userBagRepository.findByProduct_IdAndUser_Id(userBagDTO.getProductId(), userId)
                .ifPresent(existingBag -> {
                    throw new DuplicateUserBagException();
                });
        userBagRepository.save(userBagMapper.toEntity(userBagDTO));

        return getUserBag();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserBagDTO> getUserBag() {
        log.debug("getUserBag() called");
        Long userId = SecurityUtils.getCurrentUserId()
                .orElseThrow(() -> new IllegalStateException("No logged in user found in security context!"));
        return userBagRepository.findByUser_id(userId)
                .stream()
                .map(userBagMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

}
