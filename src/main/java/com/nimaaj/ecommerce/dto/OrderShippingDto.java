package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.ShippingStatus;


public class OrderShippingDto {

    private Long id;
    private Long orderId;
    private Long resourceId;
    private Long sendDateTime;
    private Long deliverDateTime;
    private ShippingStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getSendDateTime() {
        return sendDateTime;
    }

    public void setSendDateTime(Long sendDateTime) {
        this.sendDateTime = sendDateTime;
    }

    public Long getDeliverDateTime() {
        return deliverDateTime;
    }

    public void setDeliverDateTime(Long deliverDateTime) {
        this.deliverDateTime = deliverDateTime;
    }

    public ShippingStatus getStatus() {
        return status;
    }

    public void setStatus(ShippingStatus status) {
        this.status = status;
    }
}
