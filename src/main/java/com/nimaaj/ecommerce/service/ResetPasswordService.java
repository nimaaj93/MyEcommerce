package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.model.ResetPasswordResult;
import com.nimaaj.ecommerce.model.input.ResetPassModel;

public interface ResetPasswordService {

    ResetPasswordResult resetPasswordRequest(String emailOrMobile);

    String validate(String code);

    void resetPass(ResetPassModel resetPassModel);

}