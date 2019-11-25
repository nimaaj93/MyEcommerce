package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.OtpRequest;
import com.nimaaj.ecommerce.domain.User;
import com.nimaaj.ecommerce.enumaration.NotificationChannel;
import com.nimaaj.ecommerce.enumaration.NotificationTemplates;
import com.nimaaj.ecommerce.exception.InvalidOtpCodeException;
import com.nimaaj.ecommerce.exception.UserNotFoundException;
import com.nimaaj.ecommerce.repository.OtpRequestRepository;
import com.nimaaj.ecommerce.repository.UserRepository;
import com.nimaaj.ecommerce.service.NotificationService;
import com.nimaaj.ecommerce.service.OtpService;
import com.nimaaj.ecommerce.util.AuthenticationUtil;
import com.nimaaj.ecommerce.util.properties.EcommerceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;

@Service
@Transactional
@Validated
public class OtpServiceImpl implements OtpService {

    private final static Logger LOGGER = LoggerFactory.getLogger(OtpServiceImpl.class);

    private final EcommerceProperties ecommerceProperties;
    private final OtpRequestRepository otpRequestRepository;
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    public OtpServiceImpl(EcommerceProperties ecommerceProperties, OtpRequestRepository otpRequestRepository, UserRepository userRepository, NotificationService notificationService) {
        this.ecommerceProperties = ecommerceProperties;
        this.otpRequestRepository = otpRequestRepository;
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    @Override
    public void generateAndSendOtpForUser(User user) {
        String otp = AuthenticationUtil.generateOtp(ecommerceProperties.getSecurity().getOtpLength());
        String hashedOtp = AuthenticationUtil.hashPassword(otp, user.getAuthentication().getSalt(), ecommerceProperties.getSecurity().getPassHashKey());
        OtpRequest otpRequest = new OtpRequest();
        otpRequest.setOtpCode(hashedOtp);
        otpRequest.setUsed(false);
        otpRequest.setUser(user);
        otpRequestRepository.save(otpRequest);

        //TODO add params
        ArrayList<String> params = new ArrayList<>();

        notificationService.sendTemplateNotification(
                NotificationChannel.SMS,
                user.getId(),
                NotificationTemplates.OTP.getCode(),
                params);
    }

    @Override
    public void verifyOtpForUser(User user, String otp) {
        long verifyRequestDateTime = System.currentTimeMillis();
        String hashedOtp = AuthenticationUtil.hashPassword(
                otp, user.getAuthentication().getSalt(), ecommerceProperties.getSecurity().getPassHashKey());
        otpRequestRepository.findTopByUser_IdAndUsedIsFalseOrderByCreateDateTimeDesc(user.getId())
                    .filter(otpRequest ->
                            otpRequest.getOtpCode().equals(hashedOtp)
                        && verifyRequestDateTime < (otpRequest.getCreateDateTime().getTime() +
                                    (ecommerceProperties.getSecurity().getOtpValiditySeconds() * 1000))
                    )
                    .map(otpRequest -> {
                        otpRequest.setUsed(true);
                        otpRequest = otpRequestRepository.save(otpRequest);
                        return otpRequest;
                    }).orElseThrow(InvalidOtpCodeException::new);
    }
}