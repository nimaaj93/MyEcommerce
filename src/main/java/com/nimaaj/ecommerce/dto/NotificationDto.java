package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.NotificationChannel;
import lombok.Data;

@Data
public class NotificationDto {

    private Long id;
    private String recipients;
    private NotificationChannel notificationChannel;
    private Long sendDateTime;
    private Long deliverDateTime;
    private String fromSender;

}