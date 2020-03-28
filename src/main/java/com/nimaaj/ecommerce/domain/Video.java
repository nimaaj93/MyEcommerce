package com.nimaaj.ecommerce.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("VIDEO")
@Data
@EqualsAndHashCode(callSuper = true)
public class Video extends Media {

    private Long height;
    private Long width;
    private Long lengthMs;

}
