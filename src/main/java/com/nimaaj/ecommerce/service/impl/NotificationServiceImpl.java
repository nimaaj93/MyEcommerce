package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.NotificationLog;
import com.nimaaj.ecommerce.domain.NotificationTemplate;
import com.nimaaj.ecommerce.domain.User;
import com.nimaaj.ecommerce.enumaration.NotificationChannel;
import com.nimaaj.ecommerce.exception.NotificationTemplateNotFoundException;
import com.nimaaj.ecommerce.exception.UserNotFoundException;
import com.nimaaj.ecommerce.repository.NotificationLogRepository;
import com.nimaaj.ecommerce.repository.NotificationTemplateRepository;
import com.nimaaj.ecommerce.repository.UserRepository;
import com.nimaaj.ecommerce.service.NotificationSender;
import com.nimaaj.ecommerce.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Service
@Transactional
@Validated
public class NotificationServiceImpl implements NotificationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final NotificationTemplateRepository notificationTemplateRepository;
    private final NotificationLogRepository notificationLogRepository;
    private final Map<String, NotificationSender> notificationSenderMap;
    private final UserRepository userRepository;

    public NotificationServiceImpl(NotificationTemplateRepository notificationTemplateRepository,
                                   NotificationLogRepository notificationLogRepository,
                                   Map<String, NotificationSender> notificationSenderMap, UserRepository userRepository) {
        this.notificationTemplateRepository = notificationTemplateRepository;
        this.notificationLogRepository = notificationLogRepository;
        this.notificationSenderMap = notificationSenderMap;
        this.userRepository = userRepository;
    }

    @Override
    public void sendTemplateNotification(NotificationChannel notificationChannel, Long userId, String templateCode, ArrayList<String> params) {
        if (notificationChannel != NotificationChannel.SMS) {
            //TODO add support for other channels
            throw new IllegalArgumentException("Unsupported channel!");
        }
        String recipient = userRepository.findById(userId)
                .map(User::getMobileNumber)
                .orElseThrow(UserNotFoundException::new);
        NotificationTemplate notificationTemplate = notificationTemplateRepository.findByTemplateCode(templateCode)
                .orElseThrow(NotificationTemplateNotFoundException::new);
        String message = String.format(notificationTemplate.getTemplateText(), params.toArray());

        notificationSenderMap.get("smsNotificationSender").send(message, recipient);
        //TODO dynamic with proper from
        saveLog(notificationChannel, "from", message, recipient);
    }

    private void saveLog(NotificationChannel notificationChannel, String from, String content, String recipient) {
        NotificationLog notificationLog = new NotificationLog();
        notificationLog.setNotificationChannel(notificationChannel);
        notificationLog.setSendDateTime(new Date());
        notificationLog.setRecipients(recipient);
        notificationLog.setFromSender(from);
        notificationLogRepository.save(notificationLog);
    }

}