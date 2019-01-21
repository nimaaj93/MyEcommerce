package com.nimaaj.ecommerce.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("VIDEO")
public class Video extends Media {

    private Long height;
    private Long width;
    private Long lengthMs;

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getLengthMs() {
        return lengthMs;
    }

    public void setLengthMs(Long lengthMs) {
        this.lengthMs = lengthMs;
    }
}
