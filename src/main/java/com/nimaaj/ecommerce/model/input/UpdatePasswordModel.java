package com.nimaaj.ecommerce.model.input;

import javax.validation.constraints.NotBlank;

public class UpdatePasswordModel {

    @NotBlank
    private String oldPassword;
    @NotBlank
    //TODO add password validator
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