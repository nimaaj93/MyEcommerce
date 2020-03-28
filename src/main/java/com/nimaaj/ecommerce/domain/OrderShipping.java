package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.ShippingStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_shipping")
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderShipping extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Order order;
    @OneToOne
    private ShippingResource resource;
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendDateTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliverDateTime;
    @Enumerated(EnumType.STRING)
    private ShippingStatus status;

}
