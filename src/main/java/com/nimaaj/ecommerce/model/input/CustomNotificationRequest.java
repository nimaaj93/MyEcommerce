package com.nimaaj.ecommerce.model.input;

import com.nimaaj.ecommerce.enumaration.NotificationChannel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class CustomNotificationRequest {

    @NotNull
    @NotEmpty
    private Set<Long> userIds;
    @NotBlank
    private String body;
    @NotNull
    private NotificationChannel channel;

    public Set<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(Set<Long> userIds) {
        this.userIds = userIds;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    public void setChannel(NotificationChannel channel) {
        this.channel = channel;
    }
}