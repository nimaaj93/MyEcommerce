package com.nimaaj.ecommerce.model.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthenticateModel {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private boolean rememberMe;

}
