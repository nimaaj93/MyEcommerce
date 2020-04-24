package com.nimaaj.ecommerce.model.input;

import com.nimaaj.ecommerce.enumaration.Gender;
import com.nimaaj.ecommerce.util.validation.annotation.IranPhoneNumber;
import com.nimaaj.ecommerce.util.validation.annotation.SecurePassword;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationModel {

    @NotBlank
    @IranPhoneNumber
    private String mobileNumber;
    @NotBlank
    @SecurePassword
    private String password;
    @Email
    @NotBlank
    private String email;
    @NotNull
    private Gender gender;
    @NotNull
    private Long dateOfBirth;

}
