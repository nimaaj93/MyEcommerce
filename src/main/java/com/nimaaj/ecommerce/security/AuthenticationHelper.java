package com.nimaaj.ecommerce.security;

import com.nimaaj.ecommerce.util.AuthenticationUtil;
import com.nimaaj.ecommerce.util.properties.EcommerceProperties;
import org.springframework.stereotype.Component;

/**
 * Created by K550 VX on 18.10.2019.
 */
@Component
public class AuthenticationHelper {

    private final EcommerceProperties ecommerceProperties;

    public AuthenticationHelper(EcommerceProperties ecommerceProperties) {
        this.ecommerceProperties = ecommerceProperties;
    }

    public String hashPassword(String password, String userSalt) {
        return AuthenticationUtil.hashPassword(password, userSalt, ecommerceProperties.getSecurity().getPassHashKey());
    }

    public String generateSalt() {
        return AuthenticationUtil.generateSalt(ecommerceProperties.getSecurity().getUserSaltLength());
    }

    public String generateOtp() {
        return AuthenticationUtil.generateOtp(ecommerceProperties.getSecurity().getOtpLength());
    }

}