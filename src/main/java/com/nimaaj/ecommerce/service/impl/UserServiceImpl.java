package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.Authentication;
import com.nimaaj.ecommerce.domain.User;
import com.nimaaj.ecommerce.dto.ProfileDTO;
import com.nimaaj.ecommerce.exception.InvalidInputDataException;
import com.nimaaj.ecommerce.exception.UserExistsException;
import com.nimaaj.ecommerce.model.input.UserRegistrationModel;
import com.nimaaj.ecommerce.repository.UserRepository;
import com.nimaaj.ecommerce.service.UserService;
import com.nimaaj.ecommerce.util.validation.IranPhoneNumberValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ProfileDTO getProfile() {
        return null;
    }

    @Override
    @Transactional
    public void register(UserRegistrationModel model) {

        User user = new User();
        Authentication authentication = new Authentication();

        String username = model.getUsername();
        if (new EmailValidator().isValid(username, null)) {
            userRepository.findOneWithAuthoritiesByEmail(username)
                    .ifPresent(existingUser -> {
                        throw new UserExistsException();
                    });

            user.setEmail(username);

        } else if (new IranPhoneNumberValidator().isValid(username, null)) {
            userRepository.findOneWithAuthoritiesByAuthentication_Username(username)
                    .ifPresent(existingUser -> {
                        throw new UserExistsException();
                    });
        } else {
            throw new InvalidInputDataException();
        }

    }
}
