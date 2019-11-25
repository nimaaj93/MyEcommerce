package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.NotificationChannel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notification_log")
public class NotificationLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recipients;
    @Enumerated(EnumType.STRING)
    private NotificationChannel notificationChannel;
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDateTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliverDateTime;
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

    public Date getSendDateTime() {
        return sendDateTime;
    }

    public void setSendDateTime(Date sendDateTime) {
        this.sendDateTime = sendDateTime;
    }

    public Date getDeliverDateTime() {
        return deliverDateTime;
    }

    public void setDeliverDateTime(Date deliverDateTime) {
        this.deliverDateTime = deliverDateTime;
    }

    public String getFromSender() {
        return fromSender;
    }

    public void setFromSender(String fromSender) {
        this.fromSender = fromSender;
    }
}