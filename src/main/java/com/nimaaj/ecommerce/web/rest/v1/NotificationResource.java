package com.nimaaj.ecommerce.web.rest.v1;

import com.nimaaj.ecommerce.dto.NotificationDto;
import com.nimaaj.ecommerce.model.input.CustomNotificationRequest;
import com.nimaaj.ecommerce.model.input.TemplateNotificationRequest;
import com.nimaaj.ecommerce.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
public class NotificationResource {

    private final NotificationService notificationService;

    @PostMapping("/custom")
    public ResponseEntity<NotificationDto> sendCustomNotification(@Valid CustomNotificationRequest customNotificationRequest) {
        return ResponseEntity.ok(notificationService.sendCustomNotification(customNotificationRequest));
    }

    @PostMapping("/template")
    public ResponseEntity<NotificationDto> sendTemplateNotification(@Valid TemplateNotificationRequest templateNotificationRequest) {
        return ResponseEntity.ok(notificationService.sendTemplateNotification(templateNotificationRequest));
    }

}