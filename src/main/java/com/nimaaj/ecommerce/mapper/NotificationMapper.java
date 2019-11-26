package com.nimaaj.ecommerce.mapper;

import com.nimaaj.ecommerce.domain.NotificationLog;
import com.nimaaj.ecommerce.dto.NotificationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper extends CommonMapper<NotificationLog, NotificationDTO> {
}