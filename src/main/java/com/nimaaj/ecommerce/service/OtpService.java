package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.domain.User;

public interface OtpService {

    void generateAndSendOtpForUser(User user);

    void verifyOtpForUser(User user, String otp);

}