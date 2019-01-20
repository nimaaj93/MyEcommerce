package com.nimaaj.ecommerce.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PHOTO")
public class Photo extends Media {
}
