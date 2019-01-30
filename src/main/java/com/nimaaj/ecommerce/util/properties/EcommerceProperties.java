package com.nimaaj.ecommerce.util.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "ecommerce", ignoreUnknownFields = false)
@Validated
public class EcommerceProperties {

    private final Security security = new Security();

    public Security getSecurity() {
        return security;
    }

    public static class Security {

        private Long tokenValidityInSeconds;
        private Long tokenValidityInSecondsForRememberMe;
        private String secret;
        private String base64Secret;
        private String passHashKey;
        private Integer userSaltLength;

        public Long getTokenValidityInSeconds() {
            return tokenValidityInSeconds;
        }

        public Long getTokenValidityInSecondsForRememberMe() {
            return tokenValidityInSecondsForRememberMe;
        }

        public String getSecret() {
            return secret;
        }

        public String getBase64Secret() {
            return base64Secret;
        }

        public void setTokenValidityInSeconds(Long tokenValidityInSeconds) {
            this.tokenValidityInSeconds = tokenValidityInSeconds;
        }

        public void setTokenValidityInSecondsForRememberMe(Long tokenValidityInSecondsForRememberMe) {
            this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public void setBase64Secret(String base64Secret) {
            this.base64Secret = base64Secret;
        }

        public String getPassHashKey() {
            return passHashKey;
        }

        public void setPassHashKey(String passHashKey) {
            this.passHashKey = passHashKey;
        }

        public Integer getUserSaltLength() {
            return userSaltLength;
        }

        public void setUserSaltLength(Integer userSaltLength) {
            this.userSaltLength = userSaltLength;
        }
    }

}
