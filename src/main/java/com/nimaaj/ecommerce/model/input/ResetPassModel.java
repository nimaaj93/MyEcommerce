package com.nimaaj.ecommerce.model.input;


import com.nimaaj.ecommerce.util.validation.annotation.SecurePassword;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ResetPassModel {

    @NotBlank
    private String requestCode;
    @NotBlank
    @SecurePassword
    private String newPassword;

}
