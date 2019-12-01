package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.Authentication;
import com.nimaaj.ecommerce.domain.ResetPasswordRequest;
import com.nimaaj.ecommerce.domain.User;
import com.nimaaj.ecommerce.exception.InvalidResetPassRequestException;
import com.nimaaj.ecommerce.exception.UserNotFoundException;
import com.nimaaj.ecommerce.model.ResetPasswordResult;
import com.nimaaj.ecommerce.model.input.ResetPassModel;
import com.nimaaj.ecommerce.repository.AuthenticationRepository;
import com.nimaaj.ecommerce.repository.ResetPasswordRequestRepository;
import com.nimaaj.ecommerce.repository.UserRepository;
import com.nimaaj.ecommerce.security.AuthenticationHelper;
import com.nimaaj.ecommerce.service.ResetPasswordService;
import com.nimaaj.ecommerce.util.DateUtil;
import com.nimaaj.ecommerce.util.MaskUtil;
import com.nimaaj.ecommerce.util.properties.EcommerceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
@Validated
public class ResetPasswordServiceImpl implements ResetPasswordService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ResetPasswordServiceImpl.class);

    private final UserRepository userRepository;
    private final ResetPasswordRequestRepository resetPasswordRequestRepository;
    private final AuthenticationHelper authenticationHelper;
    private final EcommerceProperties ecommerceProperties;
    private final AuthenticationRepository authenticationRepository;

    public ResetPasswordServiceImpl(UserRepository userRepository,
                                    ResetPasswordRequestRepository resetPasswordRequestRepository,
                                    AuthenticationHelper authenticationHelper,
                                    EcommerceProperties ecommerceProperties,
                                    AuthenticationRepository authenticationRepository) {
        this.userRepository = userRepository;
        this.resetPasswordRequestRepository = resetPasswordRequestRepository;
        this.authenticationHelper = authenticationHelper;
        this.ecommerceProperties = ecommerceProperties;
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public ResetPasswordResult resetPasswordRequest(String emailOrMobile) {
        LOGGER.debug("resetPasswordRequest run for {}", emailOrMobile);
        User user = userRepository.findOneWithAuthoritiesByMobileNumberOrEmail(emailOrMobile, emailOrMobile)
                .orElseThrow(UserNotFoundException::new);
        setConsumedForExistingRequests(user);
        ResetPasswordRequest resetPasswordRequest = generateResetPassword(user);
        resetPasswordRequest = resetPasswordRequestRepository.save(resetPasswordRequest);
        return new ResetPasswordResult(
                MaskUtil.maskEmail(user.getEmail()),
                resetPasswordRequest.getExpireDateTime().getTime());
    }

    private void setConsumedForExistingRequests(User user) {
        resetPasswordRequestRepository.findAllByUser_Id(user.getId())
                .stream()
                .filter(resetPasswordRequest -> !resetPasswordRequest.isConsumed())
                .map(resetPasswordRequest -> {
                    resetPasswordRequest.setConsumed(true);
                    return resetPasswordRequest;
                })
                .forEach(resetPasswordRequestRepository::save);
    }

    private ResetPasswordRequest generateResetPassword(User user) {
        long expireEpoch = DateUtil.addTimeToEpochDate(
                System.currentTimeMillis(), TimeUnit.SECONDS, ecommerceProperties.getSecurity().getResetPassCodeValiditySeconds().intValue());
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
        resetPasswordRequest.setConsumed(false);
        resetPasswordRequest.setRequestCode(authenticationHelper.generateResetPasswordRequestCode());
        resetPasswordRequest.setExpireDateTime(new Date(expireEpoch));
        resetPasswordRequest.setUser(user);
        return resetPasswordRequest;
    }

    @Override
    public String validate(String code) {
        LOGGER.debug("validate run for {}", code);
        return resetPasswordRequestRepository.findOneByRequestCode(code)
                .filter(this::isRequestValid)
                .map(ResetPasswordRequest::getRequestCode)
                .orElseThrow(InvalidResetPassRequestException::new);
    }

    private boolean isRequestValid(ResetPasswordRequest request) {
        return !request.isConsumed() && request.getExpireDateTime().after(new Date());
    }

    @Override
    public void resetPass(ResetPassModel resetPassModel) {
        LOGGER.debug("resetPass run for {}", resetPassModel);
        ResetPasswordRequest resetPasswordRequest = resetPasswordRequestRepository.findOneByRequestCode(resetPassModel.getRequestCode())
                .filter(this::isRequestValid)
                .orElseThrow(InvalidResetPassRequestException::new);

        Authentication authentication = resetPasswordRequest.getUser().getAuthentication();
        String salt = authentication.getSalt();
        String hashPassword = authenticationHelper.hashPassword(resetPassModel.getNewPassword(), salt);
        authentication.setPassword(hashPassword);
        authenticationRepository.save(authentication);
    }
}