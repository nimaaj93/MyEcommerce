package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.NotificationTemplate;
import com.nimaaj.ecommerce.dto.NotificationTemplateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationTemplateMapper extends CommonMapper<NotificationTemplate, NotificationTemplateDto> {
}
