package com.nimaaj.ecommerce.model.input;


import com.nimaaj.ecommerce.util.validation.annotation.SecurePassword;

import javax.validation.constraints.NotBlank;

public class ResetPassModel {

    @NotBlank
    private String requestCode;
    @NotBlank
    @SecurePassword
    private String newPassword;

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "ResetPassModel{" +
                "requestCode='" + requestCode + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
