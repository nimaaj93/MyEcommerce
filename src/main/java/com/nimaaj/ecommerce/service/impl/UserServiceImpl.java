package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.Authentication;
import com.nimaaj.ecommerce.domain.Authority;
import com.nimaaj.ecommerce.domain.User;
import com.nimaaj.ecommerce.dto.ProfileDTO;
import com.nimaaj.ecommerce.enumaration.UserRole;
import com.nimaaj.ecommerce.enumaration.UserType;
import com.nimaaj.ecommerce.exception.AuthorityNotFoundException;
import com.nimaaj.ecommerce.exception.InvalidCurrentPassException;
import com.nimaaj.ecommerce.exception.InvalidInputDataException;
import com.nimaaj.ecommerce.exception.UserExistsException;
import com.nimaaj.ecommerce.model.input.ResetPassModel;
import com.nimaaj.ecommerce.model.input.UserRegistrationModel;
import com.nimaaj.ecommerce.repository.AuthenticationRepository;
import com.nimaaj.ecommerce.repository.AuthorityRepository;
import com.nimaaj.ecommerce.repository.UserRepository;
import com.nimaaj.ecommerce.security.SecurityUtils;
import com.nimaaj.ecommerce.service.AuthenticationService;
import com.nimaaj.ecommerce.service.UserService;
import com.nimaaj.ecommerce.util.AuthenticationUtil;
import com.nimaaj.ecommerce.util.properties.EcommerceProperties;
import com.nimaaj.ecommerce.util.validation.IranPhoneNumberValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private AuthenticationRepository authenticationRepository;
    private AuthorityRepository authorityRepository;
    private EcommerceProperties ecommerceProperties;
    private AuthenticationService authenticationService;

    public UserServiceImpl(UserRepository userRepository,
                           AuthenticationRepository authenticationRepository,
                           AuthorityRepository authorityRepository,
                           EcommerceProperties ecommerceProperties,
                           AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.authenticationRepository = authenticationRepository;
        this.authorityRepository = authorityRepository;
        this.ecommerceProperties = ecommerceProperties;
        this.authenticationService = authenticationService;
    }

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
            userRepository.findOneWithAuthoritiesByMobileNumber(username)
                    .ifPresent(existingUser -> {
                        throw new UserExistsException();
                    });
            user.setMobileNumber(username);
        } else {
            throw new InvalidInputDataException();
        }

        Set<Authority> authorities = new HashSet<>();

        Authority userAuthority = authorityRepository.findByAuthorityVal(UserRole.USER.name());

        if (userAuthority == null) throw new AuthorityNotFoundException();

        authorities.add(userAuthority);
        user.setAuthorities(authorities);

        String userSalt = AuthenticationUtil.generateSalt(ecommerceProperties.getSecurity().getUserSaltLength());
        String hashedPass = AuthenticationUtil.hashPassword(model.getPassword(),
                userSalt,
                ecommerceProperties.getSecurity().getPassHashKey());
        authentication.setSalt(userSalt);
        authentication.setPassword(hashedPass);
        authentication.setUser(user);
        user.setAuthentication(authentication);
        user.setUserType(UserType.PUBLIC);
        user.setActivated(true);

        userRepository.save(user);
        authenticationRepository.save(authentication);

    }

    @Override
    public void resetPass(ResetPassModel model) {

        String currentHash = authenticationService.hashPassForCurrentUser(model.getCurrentPassword());
        Authentication authentication = authenticationService.getCurrentAuthentication();

        if (!authentication.getPassword().equals(currentHash)) {
            throw new InvalidCurrentPassException();
        }

        String newHash = authenticationService.hashPassForCurrentUser(model.getNewPassword());
        authentication.setPassword(newHash);

        authenticationRepository.save(authentication);
    }
}
