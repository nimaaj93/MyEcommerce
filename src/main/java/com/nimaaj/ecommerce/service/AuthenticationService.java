package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.domain.Authentication;

public interface AuthenticationService {

    String hashPassForCurrentUser(String password);

    Authentication getCurrentAuthentication();

}
