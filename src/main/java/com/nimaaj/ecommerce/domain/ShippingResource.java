package com.nimaaj.ecommerce.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "shipping_resource")
@Data
@EqualsAndHashCode(callSuper = true)
public class ShippingResource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startDateTime;
    private Date endDateTime;
    private Integer total;
    private Integer available;

}
