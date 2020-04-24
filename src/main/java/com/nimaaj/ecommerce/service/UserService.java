package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.ProfileDto;
import com.nimaaj.ecommerce.dto.UserDto;
import com.nimaaj.ecommerce.model.input.OtpVerification;
import com.nimaaj.ecommerce.model.input.UpdatePasswordModel;
import com.nimaaj.ecommerce.model.input.UserRegistrationModel;

public interface UserService {

    ProfileDto getProfile();

    UserDto register(UserRegistrationModel model);

    UserDto activateUser(OtpVerification otpVerification);

    void changePassword(UpdatePasswordModel updatePasswordModel);

}
