package com.nimaaj.ecommerce.service.impl;
import com.nimaaj.ecommerce.service.NotificationSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("smsNotificationSender")
public class SmsNotificationSender implements NotificationSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(SmsNotificationSender.class);

    //TODO add async
    @Override
    public void send(String content, String recipient) {
        LOGGER.debug("send() called for content {} and recipient {}", content, recipient);
        //TODO implement sms service call
    }
}