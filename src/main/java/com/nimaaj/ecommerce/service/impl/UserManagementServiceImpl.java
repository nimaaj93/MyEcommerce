package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.User;
import com.nimaaj.ecommerce.dto.UserDTO;
import com.nimaaj.ecommerce.enumaration.UserState;
import com.nimaaj.ecommerce.exception.UserNotFoundException;
import com.nimaaj.ecommerce.mapper.UserMapper;
import com.nimaaj.ecommerce.repository.UserRepository;
import com.nimaaj.ecommerce.service.UserManagementService;
import com.nimaaj.ecommerce.service.specification.factory.SpecificationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional
@Validated
public class UserManagementServiceImpl implements UserManagementService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserManagementServiceImpl.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SpecificationFactory<User, String> userSpecificationFactory;

    @SuppressWarnings("unchecked")
    public UserManagementServiceImpl(UserRepository userRepository,
                                     UserMapper userMapper,
                                     @Qualifier("userSearchSpecificationFactory") SpecificationFactory userSpecificationFactory) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userSpecificationFactory = (SpecificationFactory<User, String>) userSpecificationFactory;
    }

    @Override
    public Page<UserDTO> pageUsers(Pageable pageable, String query) {
        LOGGER.debug("pageUsers run for pageable {} and query {}", pageable, query);
        Specification<User> specification = userSpecificationFactory.getSpecification(query);
        return userRepository.findAll(specification, pageable).map(userMapper::toDto);
    }

    @Override
    public UserDTO enable(Long userId) {
        LOGGER.debug("enable run for {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        user.setUserState(UserState.ENABLED);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDTO disable(Long userId) {
        LOGGER.debug("disable run for {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        user.setUserState(UserState.DISABLED);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }
}