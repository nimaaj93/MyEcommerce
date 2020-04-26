package com.nimaaj.ecommerce.service;

import com.nimaaj.ecommerce.dto.NotificationTemplateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationTemplateService {

    Page<NotificationTemplateDto> getTemplates(Pageable pageable, String query);

    NotificationTemplateDto create(NotificationTemplateDto notificationTemplateDto);

    NotificationTemplateDto update(NotificationTemplateDto notificationTemplateDto);

    void delete(Long id);

    NotificationTemplateDto getNotificationTemplateByCode(String code);

    String buildNotificationWithParams(String templateCode, Object ... params);

}
