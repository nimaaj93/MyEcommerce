package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.MediaStatus;
import com.nimaaj.ecommerce.enumaration.MediaType;

/**
 * Created by K550 VX on 27.09.2019.
 */
public class MediaDTO {

    private Long id;
    private String filePath;
    private String basePath;
    private String host;
    private MediaStatus status;
    private Long size;
    private String format;
    private MediaType mediaType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public MediaStatus getStatus() {
        return status;
    }

    public void setStatus(MediaStatus status) {
        this.status = status;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }
}