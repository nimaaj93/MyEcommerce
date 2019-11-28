package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.ProfileDTO;
import com.nimaaj.ecommerce.dto.UserDTO;
import com.nimaaj.ecommerce.model.input.OtpVerification;
import com.nimaaj.ecommerce.model.input.ResetPassModel;
import com.nimaaj.ecommerce.model.input.UpdatePasswordModel;
import com.nimaaj.ecommerce.model.input.UserRegistrationModel;

public interface UserService {

    ProfileDTO getProfile();

    UserDTO register(UserRegistrationModel model);

    void resetPass(ResetPassModel model);

    UserDTO activateUser(OtpVerification otpVerification);

    void changePassword(UpdatePasswordModel updatePasswordModel);
}
