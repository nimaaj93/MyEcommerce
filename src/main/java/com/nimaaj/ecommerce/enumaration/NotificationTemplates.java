package com.nimaaj.ecommerce.enumaration;

public enum NotificationTemplates {
    OTP("otp"),
    ;

    private String code;

    NotificationTemplates(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}