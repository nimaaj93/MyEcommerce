package com.nimaaj.ecommerce.service;

public interface NotificationSender {

    void send(String content, String recipient);

}