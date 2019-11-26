package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.NotificationChannel;

public class NotificationDTO {

    private Long id;
    private String recipients;
    private NotificationChannel notificationChannel;
    private Long sendDateTime;
    private Long deliverDateTime;
    private String fromSender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public NotificationChannel getNotificationChannel() {
        return notificationChannel;
    }

    public void setNotificationChannel(NotificationChannel notificationChannel) {
        this.notificationChannel = notificationChannel;
    }

    public Long getSendDateTime() {
        return sendDateTime;
    }

    public void setSendDateTime(Long sendDateTime) {
        this.sendDateTime = sendDateTime;
    }

    public Long getDeliverDateTime() {
        return deliverDateTime;
    }

    public void setDeliverDateTime(Long deliverDateTime) {
        this.deliverDateTime = deliverDateTime;
    }

    public String getFromSender() {
        return fromSender;
    }

    public void setFromSender(String fromSender) {
        this.fromSender = fromSender;
    }
}