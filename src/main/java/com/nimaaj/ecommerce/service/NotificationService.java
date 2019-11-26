package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.NotificationDTO;
import com.nimaaj.ecommerce.model.input.CustomNotificationRequest;
import com.nimaaj.ecommerce.model.input.TemplateNotificationRequest;


public interface NotificationService {

    NotificationDTO sendTemplateNotification(TemplateNotificationRequest request);

    NotificationDTO sendCustomNotification(CustomNotificationRequest request);

}