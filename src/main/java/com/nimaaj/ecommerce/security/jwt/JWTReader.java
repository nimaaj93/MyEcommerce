package com.nimaaj.ecommerce.security.jwt;

import com.nimaaj.ecommerce.util.properties.EcommerceProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

/**
 * Created by K550 VX on 18.10.2019.
 */
@Component
public class JWTReader {

    private final EcommerceProperties ecommerceProperties;
    private final KeyProvider keyProvider;

    public JWTReader(EcommerceProperties ecommerceProperties, KeyProvider keyProvider) {
        this.ecommerceProperties = ecommerceProperties;
        this.keyProvider = keyProvider;
    }

    public String readClaim(String token, String key) {
        return obtainClaims(token).get(key, String.class);
    }

    private Claims obtainClaims(String token) {
        if (token.toLowerCase().startsWith("bearer")) {
            token = token.replaceFirst("Bearer ","");
            token = token.replaceFirst("bearer ","");
        }
        Jwt parse = Jwts.parser().setSigningKey(keyProvider.getKey()).parse(token);
        return (Claims) parse.getBody();
    }

    public <T> T readClaim(String token, String key, Class<T> type) {
        return obtainClaims(token).get(key, type);
    }

}