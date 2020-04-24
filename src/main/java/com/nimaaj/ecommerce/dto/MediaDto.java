package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.MediaStatus;
import com.nimaaj.ecommerce.enumaration.MediaType;
import lombok.Data;

/**
 * Created by K550 VX on 27.09.2019.
 */
@Data
public class MediaDto {

    private Long id;
    private String filePath;
    private String basePath;
    private String host;
    private MediaStatus status;
    private Long size;
    private String format;
    private MediaType mediaType;
    private String caption;
    private String fileUuid;

}