package com.nimaaj.ecommerce.model.input;

import javax.validation.constraints.NotBlank;

public class ResetPassModel {

    @NotBlank
    private String currentPassword;
    @NotBlank
    private String newPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
