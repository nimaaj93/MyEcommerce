package com.nimaaj.ecommerce.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("VIDEO")
public class Video extends Media {
}
