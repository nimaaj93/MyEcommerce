package com.nimaaj.ecommerce.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by K550 VX on 18.10.2019.
 */
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> grantedAuthorities;
    private Long userId;

    public CustomUserDetails(String username, String password, List<GrantedAuthority> grantedAuthorities, Long userId) {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.userId = userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getUserId() {
        return userId;
    }
}