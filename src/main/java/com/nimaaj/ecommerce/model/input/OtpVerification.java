package com.nimaaj.ecommerce.model.input;

import lombok.Data;

@Data
public class OtpVerification {

    private String mobileNumber;
    private String verificationCode;

}