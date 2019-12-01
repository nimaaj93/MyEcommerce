package com.nimaaj.ecommerce.model;

public class ResetPasswordResult {

    private String maskedEmail;
    private Long expireDateTime;

    public ResetPasswordResult() {
    }

    public ResetPasswordResult(String maskedEmail, Long expireDateTime) {
        this.maskedEmail = maskedEmail;
        this.expireDateTime = expireDateTime;
    }

    public String getMaskedEmail() {
        return maskedEmail;
    }

    public void setMaskedEmail(String maskedEmail) {
        this.maskedEmail = maskedEmail;
    }

    public Long getExpireDateTime() {
        return expireDateTime;
    }

    public void setExpireDateTime(Long expireDateTime) {
        this.expireDateTime = expireDateTime;
    }
}