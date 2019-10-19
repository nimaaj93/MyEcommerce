package com.nimaaj.ecommerce.mapper;

import org.mapstruct.Mapper;

import java.util.Date;

/**
 * Created by K550 VX on 18.10.2019.
 */
@Mapper(componentModel = "spring")
public interface DateMapper {

    default Date toDate(Long epochMilli) {
        if (epochMilli == null) {
            return null;
        }
        return new Date(epochMilli);
    }

    default Long toEpochMilli(Date date) {
        if (date == null) {
            return null;
        }
        return date.getTime();
    }

}