package com.nimaaj.ecommerce.dto;

import lombok.Data;

@Data
public class ApplicationDto {

    private Long id;
    private String titleFa;
    private String titleEn;
    private boolean setupDone;
    private String description;

}