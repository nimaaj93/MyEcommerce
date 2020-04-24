package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserManagementService {

    Page<UserDto> pageUsers(Pageable pageable, String query);

    UserDto enable(Long userId);

    UserDto disable(Long userId);

}