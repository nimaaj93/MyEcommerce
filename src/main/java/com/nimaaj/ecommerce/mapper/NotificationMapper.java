package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.NotificationLog;
import com.nimaaj.ecommerce.dto.NotificationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { DateMapper.class })
public interface NotificationMapper extends CommonMapper<NotificationLog, NotificationDto> {
}