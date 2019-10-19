package com.nimaaj.ecommerce.security.jwt;

import com.nimaaj.ecommerce.util.properties.EcommerceProperties;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;

/**
 * Created by K550 VX on 18.10.2019.
 */
@Component
public class KeyProvider {

    private final static Logger log = LoggerFactory.getLogger(KeyProvider.class);

    private Key key;

    private final EcommerceProperties ecommerceProperties;

    public KeyProvider(EcommerceProperties ecommerceProperties) {
        this.ecommerceProperties = ecommerceProperties;
    }

    @PostConstruct
    public void init() {
        byte[] keyBytes;
        String secret = ecommerceProperties.getSecurity().getSecret();
        if (!StringUtils.isEmpty(secret)) {
            log.warn("Warning: the JWT key used is not Base64-encoded. " +
                    "We recommend using the `jhipster.security.authentication.jwt.base64-secret` key for optimum security.");
            keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        } else {
            log.debug("Using a Base64-encoded JWT secret key");
            keyBytes = Decoders.BASE64.decode(ecommerceProperties.getSecurity().getBase64Secret());
        }
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public Key getKey() {
        return key;
    }
}