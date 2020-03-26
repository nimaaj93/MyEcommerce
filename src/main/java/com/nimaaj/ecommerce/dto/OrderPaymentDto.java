package com.nimaaj.ecommerce.dto;

import com.nimaaj.ecommerce.enumaration.PaymentMethod;
import com.nimaaj.ecommerce.enumaration.PaymentProvider;
import com.nimaaj.ecommerce.enumaration.PaymentStatus;


public class OrderPaymentDto extends BaseEntityDto {

    private Long id;
    private Long amount;
    private Long orderId;
    private PaymentStatus status;
    private PaymentMethod method;
    private PaymentProvider provider;
    private String requestId;
    private Long paymentDateTime;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public PaymentProvider getProvider() {
        return provider;
    }

    public void setProvider(PaymentProvider provider) {
        this.provider = provider;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Long getPaymentDateTime() {
        return paymentDateTime;
    }

    public void setPaymentDateTime(Long paymentDateTime) {
        this.paymentDateTime = paymentDateTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
