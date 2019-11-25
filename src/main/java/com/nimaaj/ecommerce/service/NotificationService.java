package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.enumaration.NotificationChannel;

import java.util.ArrayList;

public interface NotificationService {

    void sendTemplateNotification(NotificationChannel notificationChannel, Long userId, String templateCode, ArrayList<String> params);

}