package com.nimaaj.ecommerce.domain;

import com.nimaaj.ecommerce.enumaration.ShippingStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_shipping")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ShippingResource getResource() {
        return resource;
    }

    public void setResource(ShippingResource resource) {
        this.resource = resource;
    }

    public Date getSendDateTime() {
        return sendDateTime;
    }

    public void setSendDateTime(Date sendDateTime) {
        this.sendDateTime = sendDateTime;
    }

    public Date getDeliverDateTime() {
        return deliverDateTime;
    }

    public void setDeliverDateTime(Date deliverDateTime) {
        this.deliverDateTime = deliverDateTime;
    }

    public ShippingStatus getStatus() {
        return status;
    }

    public void setStatus(ShippingStatus status) {
        this.status = status;
    }
}
