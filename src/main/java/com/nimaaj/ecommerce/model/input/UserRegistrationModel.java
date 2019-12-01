package com.nimaaj.ecommerce.model.input;

import com.nimaaj.ecommerce.enumaration.Gender;
import com.nimaaj.ecommerce.util.validation.annotation.IranPhoneNumber;
import com.nimaaj.ecommerce.util.validation.annotation.SecurePassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
