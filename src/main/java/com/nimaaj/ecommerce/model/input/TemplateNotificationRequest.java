package com.nimaaj.ecommerce.model.input;

import com.nimaaj.ecommerce.enumaration.NotificationChannel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Set;

public class TemplateNotificationRequest {

    @NotNull
    @NotEmpty
    private Set<Long> userIds;
    @NotBlank
    private String templateCode;
    @NotNull
    private NotificationChannel notificationChannel;
    private ArrayList<String> params = new ArrayList<>();

    public Set<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(Set<Long> userIds) {
        this.userIds = userIds;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public NotificationChannel getNotificationChannel() {
        return notificationChannel;
    }

    public void setNotificationChannel(NotificationChannel notificationChannel) {
        this.notificationChannel = notificationChannel;
    }

    public ArrayList<String> getParams() {
        return params;
    }

    public void setParams(ArrayList<String> params) {
        this.params = params;
    }
}