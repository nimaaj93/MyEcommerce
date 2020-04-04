package com.nimaaj.ecommerce.security;

import com.nimaaj.ecommerce.security.jwt.JWTReader;
import com.nimaaj.ecommerce.security.jwt.TokenProvider;
import com.nimaaj.ecommerce.util.context.ApplicationContextAccessor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static Optional<String> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
            .map(authentication -> {
                if (authentication.getPrincipal() instanceof UserDetails) {
                    UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                    return springSecurityUser.getUsername();
                } else if (authentication.getPrincipal() instanceof String) {
                    return (String) authentication.getPrincipal();
                }
                return null;
            });
    }

    public static Optional<String> getCurrentUserJWT() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
            .filter(authentication -> authentication.getCredentials() instanceof String)
            .map(authentication -> (String) authentication.getCredentials());
    }

    public static boolean isAuthenticated() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
            .map(authentication -> authentication.getAuthorities().stream()
                .noneMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(AuthoritiesConstants.ANONYMOUS)))
            .orElse(false);
    }

    public static boolean isCurrentUserInRole(String authority) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
            .map(authentication -> authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authority)))
            .orElse(false);
    }

    public static Optional<Long> getCurrentUserId() {
        return getCurrentUserJWT().map(token -> getUserId(token).get());
    }

    public static Optional<Long> getCurrentCustomerId() {
        return getCurrentUserJWT().map(token -> getCustomerId(token).get());
    }

    public static Optional<Long> getUserId(String token) {
        try {
            return readClaimValue(token, TokenProvider.USER_ID_KEY, Integer.class)
                    .map(Integer::longValue);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<Long> getCustomerId(String token) {
        try {
            return readClaimValue(token, TokenProvider.CUSTOMER_ID_KEY, Integer.class)
                    .map(Integer::longValue);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static boolean isAdmin(String token) {
        return readClaimValue(token, TokenProvider.ADMIN_KEY, Boolean.class)
                .orElseThrow(IllegalStateException::new);
    }

    public static boolean isCurrentUserAdmin() {
        return getCurrentUserJWT()
                .map(SecurityUtils::isAdmin)
                .orElseThrow(IllegalStateException::new);
    }

    public static <T> Optional<T> readClaimValue(String token, String claim, Class<T> clazz) {
        try {
            return Optional.ofNullable(
                    ApplicationContextAccessor
                            .getApplicationContext()
                            .getBean(JWTReader.class)
                            .readClaim(token, claim, clazz)
            );
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
