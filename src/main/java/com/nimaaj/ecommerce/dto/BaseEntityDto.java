package com.nimaaj.ecommerce.dto;

import lombok.Data;

@Data
public class BaseEntityDto {

    //TODO add json views
    private Long createDateTime;

    private String createUser;

    private Long lastModifiedDateTime;

    private String updateUser;

}
