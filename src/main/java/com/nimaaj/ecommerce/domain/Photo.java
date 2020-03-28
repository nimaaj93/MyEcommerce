package com.nimaaj.ecommerce.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PHOTO")
@Data
@EqualsAndHashCode(callSuper = true)
public class Photo extends Media {

    private Long width;
    private Long height;
    private Double aspectRatio;
    private boolean blackAndWhite;

}
