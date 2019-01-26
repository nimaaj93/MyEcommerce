package com.nimaaj.ecommerce.security;

import com.nimaaj.ecommerce.domain.User;
import com.nimaaj.ecommerce.repository.UserRepository;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        if (new EmailValidator().isValid(login, null)) {
            return userRepository.findOneWithAuthoritiesByEmail(login)
                .map(user -> createSpringSecurityUser(login, user))
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + login + " was not found in the database"));
        }

        return userRepository.findOneWithAuthoritiesByAuthentication_Username(login)
            .map(user -> createSpringSecurityUser(login, user))
            .orElseThrow(() -> new UsernameNotFoundException("User " + login + " was not found in the database"));

    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String username, User user) {
        if (!user.isActivated()) {
            throw new UserNotActivatedException("User " + username + " was not activated");
        }
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityVal()))
            .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getAuthentication().getUsername(),
            user.getAuthentication().getPassword(),
            grantedAuthorities);
    }
}
