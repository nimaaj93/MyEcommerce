package com.nimaaj.ecommerce.util.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix = "ecommerce", ignoreUnknownFields = false)
@Validated
public class EcommerceProperties {

    @Valid
    private final Security security = new Security();
    @Valid
    private final Storage storage = new Storage();

    public Security getSecurity() {
        return security;
    }

    public Storage getStorage() {
        return storage;
    }

    public static class Security {

        @NotNull
        private Long tokenValidityInSeconds;
        @NotNull
        private Long tokenValidityInSecondsForRememberMe;
        @NotBlank
        private String secret;
        @NotBlank
        private String base64Secret;
        @NotBlank
        private String passHashKey;
        @NotNull
        private Integer userSaltLength;
        @NotNull
        private Integer otpLength;
        @NotNull
        private Integer resetPassCodeLength;
        @NotNull
        private Long otpValiditySeconds;
        @NotNull
        private Long resetPassCodeValiditySeconds;

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

        public Integer getOtpLength() {
            return otpLength;
        }

        public void setOtpLength(Integer otpLength) {
            this.otpLength = otpLength;
        }

        public Long getOtpValiditySeconds() {
            return otpValiditySeconds;
        }

        public void setOtpValiditySeconds(Long otpValiditySeconds) {
            this.otpValiditySeconds = otpValiditySeconds;
        }

        public Integer getResetPassCodeLength() {
            return resetPassCodeLength;
        }

        public void setResetPassCodeLength(Integer resetPassCodeLength) {
            this.resetPassCodeLength = resetPassCodeLength;
        }

        public Long getResetPassCodeValiditySeconds() {
            return resetPassCodeValiditySeconds;
        }

        public void setResetPassCodeValiditySeconds(Long resetPassCodeValiditySeconds) {
            this.resetPassCodeValiditySeconds = resetPassCodeValiditySeconds;
        }
    }

    public static class Storage {

        @NotBlank
        private String basePath;

        public String getBasePath() {
            return basePath;
        }

        public void setBasePath(String basePath) {
            this.basePath = basePath;
        }
    }

}
