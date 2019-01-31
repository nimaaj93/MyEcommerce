package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.ProfileDTO;
import com.nimaaj.ecommerce.model.input.ResetPassModel;
import com.nimaaj.ecommerce.model.input.UserRegistrationModel;

public interface UserService {

    ProfileDTO getProfile();

    void register(UserRegistrationModel model);

    void resetPass(ResetPassModel model);

}
