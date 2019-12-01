package com.nimaaj.ecommerce.model.input;

import com.nimaaj.ecommerce.util.validation.annotation.SecurePassword;

import javax.validation.constraints.NotBlank;

public class UpdatePasswordModel {

    @NotBlank
    private String oldPassword;
    @NotBlank
    @SecurePassword
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}