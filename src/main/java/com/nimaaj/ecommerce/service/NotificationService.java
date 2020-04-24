package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.NotificationDto;
import com.nimaaj.ecommerce.model.input.CustomNotificationRequest;
import com.nimaaj.ecommerce.model.input.TemplateNotificationRequest;


public interface NotificationService {

    NotificationDto sendTemplateNotification(TemplateNotificationRequest request);

    NotificationDto sendCustomNotification(CustomNotificationRequest request);

}