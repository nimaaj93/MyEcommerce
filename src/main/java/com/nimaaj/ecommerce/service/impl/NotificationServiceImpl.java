package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.NotificationLog;
import com.nimaaj.ecommerce.domain.NotificationTemplate;
import com.nimaaj.ecommerce.domain.User;
import com.nimaaj.ecommerce.dto.NotificationDTO;
import com.nimaaj.ecommerce.enumaration.NotificationChannel;
import com.nimaaj.ecommerce.exception.NotificationTemplateNotFoundException;
import com.nimaaj.ecommerce.exception.UserNotFoundException;
import com.nimaaj.ecommerce.mapper.NotificationMapper;
import com.nimaaj.ecommerce.model.input.CustomNotificationRequest;
import com.nimaaj.ecommerce.model.input.TemplateNotificationRequest;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@Validated
public class NotificationServiceImpl implements NotificationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private final NotificationTemplateRepository notificationTemplateRepository;
    private final NotificationLogRepository notificationLogRepository;
    private final Map<String, NotificationSender> notificationSenderMap;
    private final UserRepository userRepository;
    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationTemplateRepository notificationTemplateRepository,
                                   NotificationLogRepository notificationLogRepository,
                                   Map<String, NotificationSender> notificationSenderMap,
                                   UserRepository userRepository,
                                   NotificationMapper notificationMapper) {
        this.notificationTemplateRepository = notificationTemplateRepository;
        this.notificationLogRepository = notificationLogRepository;
        this.notificationSenderMap = notificationSenderMap;
        this.userRepository = userRepository;
        this.notificationMapper = notificationMapper;
    }

    private NotificationLog saveLog(NotificationChannel notificationChannel, String from, String content, String recipient) {
        NotificationLog notificationLog = new NotificationLog();
        notificationLog.setNotificationChannel(notificationChannel);
        notificationLog.setSendDateTime(new Date());
        notificationLog.setRecipients(recipient);
        notificationLog.setFromSender(from);
        return notificationLogRepository.save(notificationLog);
    }

    @Override
    public NotificationDTO sendTemplateNotification(TemplateNotificationRequest request) {
        if (request.getNotificationChannel() != NotificationChannel.SMS) {
            //TODO add support for other channels
            throw new IllegalArgumentException("Unsupported channel!");
        }
        NotificationTemplate notificationTemplate = notificationTemplateRepository.findByTemplateCode(request.getTemplateCode())
                .orElseThrow(NotificationTemplateNotFoundException::new);
        String message = String.format(notificationTemplate.getTemplateText(), request.getParams().toArray());

        String recipients = request.getUserIds().stream()
                .map(userId -> userRepository.findById(userId)
                        .map(request.getNotificationChannel().getFunction())
                        .orElseThrow(UserNotFoundException::new)
                ).collect(Collectors.joining(","));

        notificationSenderMap.get("smsNotificationSender").send(message, recipients);
        //TODO dynamic with proper from
        NotificationLog notificationLog = saveLog(request.getNotificationChannel(), "from", message, recipients);
        return notificationMapper.toDto(notificationLog);
    }

    @Override
    public NotificationDTO sendCustomNotification(CustomNotificationRequest request) {
        String recipients = request.getUserIds().stream()
                .map(userId -> userRepository.findById(userId)
                        .map(request.getChannel().getFunction())
                        .orElseThrow(UserNotFoundException::new)
                ).collect(Collectors.joining(","));
        notificationSenderMap.get("smsNotificationSender").send(request.getBody(), recipients);
        //TODO dynamic with proper from
        NotificationLog notificationLog = saveLog(request.getChannel(), "from", request.getBody(), recipients);
        return notificationMapper.toDto(notificationLog);
    }
}