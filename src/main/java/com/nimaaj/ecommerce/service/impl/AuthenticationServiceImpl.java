package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.Authentication;
import com.nimaaj.ecommerce.repository.AuthenticationRepository;
import com.nimaaj.ecommerce.security.SecurityUtils;
import com.nimaaj.ecommerce.service.AuthenticationService;
import com.nimaaj.ecommerce.util.AuthenticationUtil;
import com.nimaaj.ecommerce.util.properties.EcommerceProperties;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private EcommerceProperties ecommerceProperties;
    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Override
    public String hashPassForCurrentUser(String password) {

        String hashKey = ecommerceProperties.getSecurity().getPassHashKey();
        String userSalt = getCurrentAuthentication().getSalt();

        return AuthenticationUtil.hashPassword(password, userSalt, hashKey);
    }

    @Override
    public Authentication getCurrentAuthentication() {

        String login = SecurityUtils.getCurrentUserLogin()
                .orElseThrow(() -> new IllegalStateException());

        Authentication authentication;

        if (new EmailValidator().isValid(login, null)) {
            authentication = authenticationRepository.findByUser_Email(login);
        } else  {
            authentication = authenticationRepository.findByUser_MobileNumber(login);
        }

        return authentication;
    }
}
