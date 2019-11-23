package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserManagementService {

    Page<UserDTO> pageUsers(Pageable pageable, String query);

    UserDTO enable(Long userId);

    UserDTO disable(Long userId);

}