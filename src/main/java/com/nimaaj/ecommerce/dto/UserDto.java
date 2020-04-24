package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.Gender;
import com.nimaaj.ecommerce.enumaration.UserState;
import com.nimaaj.ecommerce.enumaration.UserType;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String mobileNumber;
    private String nationalCode;
    private String email;
    private Gender gender;
    private UserType userType;
    private Long dateOfBirth;
    private UserState userState;
    private CustomerDto customer;

}