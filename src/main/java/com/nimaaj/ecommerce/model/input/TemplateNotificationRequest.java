package com.nimaaj.ecommerce.model.input;

import com.nimaaj.ecommerce.enumaration.NotificationChannel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Set;

@Data
public class TemplateNotificationRequest {

    @NotNull
    @NotEmpty
    private Set<Long> userIds;
    @NotBlank
    private String templateCode;
    @NotNull
    private NotificationChannel notificationChannel;
    private ArrayList<String> params = new ArrayList<>();

}